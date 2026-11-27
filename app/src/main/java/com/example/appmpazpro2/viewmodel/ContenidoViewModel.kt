package com.example.appmpazpro2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmpazpro2.data.ContenidoDataStore
import com.example.appmpazpro2.data.ContenidoLocalStorage
import com.example.appmpazpro2.model.ContenidoErrores
import com.example.appmpazpro2.model.ContenidoUiState
import com.example.appmpazpro2.model.EstadoContenido
import com.example.appmpazpro2.repository.ContenidoRepository
import com.example.appmpazpro2.repository.ContenidoRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ContenidoViewModel(
    application: Application,
    private val repository: ContenidoRepository,
    private val localStorage: ContenidoLocalStorage,
    cargarAlIniciar: Boolean = true
) : AndroidViewModel(application) {

    constructor(application: Application) : this(
        application = application,
        repository = ContenidoRepositoryImpl(),
        localStorage = ContenidoDataStore(application),
        cargarAlIniciar = true
    )

    // STATEFLOW: guarda el estado actual del formulario
    private val _uiState = MutableStateFlow(ContenidoUiState())

    // STATEFLOW: expone el estado para que la interfaz lo observe
    val uiState: StateFlow<ContenidoUiState> = _uiState.asStateFlow()

    private val _errores = MutableStateFlow(ContenidoErrores())
    val errores: StateFlow<ContenidoErrores> = _errores.asStateFlow()

    // STATEFLOW: mantiene la lista actual de contenidos
    private val _contenidos = MutableStateFlow<List<ContenidoUiState>>(emptyList())
    val contenidos: StateFlow<List<ContenidoUiState>> = _contenidos.asStateFlow()

    private val _sincronizando = MutableStateFlow(false)
    val sincronizando: StateFlow<Boolean> = _sincronizando.asStateFlow()

    private val _mensajeSincronizacion = MutableStateFlow<String?>(null)
    val mensajeSincronizacion: StateFlow<String?> = _mensajeSincronizacion.asStateFlow()

    init {
        if (cargarAlIniciar) {
            cargarContenidosIniciales()
        }
    }

    private fun cargarContenidosIniciales() {
        viewModelScope.launch {
            runCatching {
                val locales = localStorage.obtenerContenidos().first()
                if (locales.isNotEmpty() && _contenidos.value.isEmpty()) {
                    _contenidos.value = locales
                }
            }
            sincronizarConBackend()
        }
    }

    // CRUD READ REMOTO (GET /api/contenidos) con respaldo en DataStore
    fun sincronizarConBackend() {
        viewModelScope.launch {
            _sincronizando.value = true
            try {
                val remotos = repository.obtenerContenidos()
                _contenidos.value = remotos
                localStorage.guardarContenidos(remotos)
                _mensajeSincronizacion.value = "Sincronizado con el backend (${remotos.size} contenidos)"
            } catch (e: Exception) {
                _mensajeSincronizacion.value =
                    "Modo respaldo local (backend no disponible: ${e.message ?: "sin conexion"})"
            } finally {
                _sincronizando.value = false
            }
        }
    }

    fun actualizarAsignatura(valor: String) {
        _uiState.value = _uiState.value.copy(asignatura = valor)
        _errores.value = _errores.value.copy(asignatura = null)
    }

    fun actualizarUnidad(valor: String) {
        _uiState.value = _uiState.value.copy(unidad = valor)
        _errores.value = _errores.value.copy(unidad = null)
    }

    fun actualizarObjetivo(valor: String) {
        _uiState.value = _uiState.value.copy(objetivo = valor)
        _errores.value = _errores.value.copy(objetivo = null)
    }

    fun actualizarTipo(valor: String) {
        _uiState.value = _uiState.value.copy(tipo = valor)
        _errores.value = _errores.value.copy(tipo = null)
    }

    fun actualizarTitulo(valor: String) {
        _uiState.value = _uiState.value.copy(titulo = valor)
        _errores.value = _errores.value.copy(titulo = null)
    }

    fun actualizarContenido(valor: String) {
        _uiState.value = _uiState.value.copy(contenido = valor)
        _errores.value = _errores.value.copy(contenido = null)
    }

    fun actualizarPregunta(valor: String) {
        _uiState.value = _uiState.value.copy(pregunta = valor)
        _errores.value = _errores.value.copy(pregunta = null)
    }

    fun actualizarImagen(uri: String?) {
        _uiState.value = _uiState.value.copy(imagenUri = uri)
    }

    // VALIDACIONES: valida los campos obligatorios del formulario
    fun validarFormulario(): Boolean {
        val estadoActual = _uiState.value

        val nuevosErrores = ContenidoErrores(
            asignatura = if (estadoActual.asignatura.isBlank()) "La asignatura es obligatoria" else null,
            unidad = if (estadoActual.unidad.isBlank()) "La unidad es obligatoria" else null,
            objetivo = if (estadoActual.objetivo.isBlank()) "El objetivo es obligatorio" else null,
            tipo = if (estadoActual.tipo.isBlank()) "El tipo es obligatorio" else null,
            titulo = if (estadoActual.titulo.isBlank()) "El titulo es obligatorio" else null,
            contenido = if (estadoActual.contenido.isBlank()) "El contenido es obligatorio" else null,
            pregunta = if (estadoActual.pregunta.isBlank()) "La pregunta es obligatoria" else null
        )

        _errores.value = nuevosErrores

        return nuevosErrores.asignatura == null &&
            nuevosErrores.unidad == null &&
            nuevosErrores.objetivo == null &&
            nuevosErrores.tipo == null &&
            nuevosErrores.titulo == null &&
            nuevosErrores.contenido == null &&
            nuevosErrores.pregunta == null
    }

    // CRUD CREATE: crea localmente y envia POST /api/contenidos al backend
    fun guardarContenido(rol: RolUsuario?): Boolean {
        // REGLA DE NEGOCIO: solo UTP y Docente pueden crear contenidos
        if (rol != RolUsuario.UTP && rol != RolUsuario.DOCENTE) {
            return false
        }

        if (!validarFormulario()) {
            return false
        }

        if (rol == RolUsuario.DOCENTE && !_uiState.value.asignatura.equals("Lenguaje", ignoreCase = true)) {
            _errores.value = _errores.value.copy(
                asignatura = "El docente solo puede trabajar con Lenguaje"
            )
            return false
        }

        val nuevoIdTemporal = (_contenidos.value.maxOfOrNull { it.id } ?: 0) + 1

        // REGLA DE NEGOCIO: UTP aprueba directamente y Docente deja el contenido pendiente
        val estadoInicial = if (rol == RolUsuario.UTP) {
            EstadoContenido.APROBADO
        } else {
            EstadoContenido.PENDIENTE
        }

        val nuevoContenido = _uiState.value.copy(
            id = nuevoIdTemporal,
            estado = estadoInicial
        )

        val nuevaLista = _contenidos.value + nuevoContenido
        guardarLista(nuevaLista)
        limpiarFormulario()

        viewModelScope.launch {
            try {
                val creadoRemoto = repository.crearContenido(nuevoContenido)
                val listaActualizada = _contenidos.value.map { item ->
                    if (item.id == nuevoIdTemporal) creadoRemoto else item
                }
                guardarLista(listaActualizada)
                _mensajeSincronizacion.value = "Contenido creado en el servidor (ID #${creadoRemoto.id})"
            } catch (e: Exception) {
                _mensajeSincronizacion.value =
                    "Guardado localmente (POST pendiente: ${e.message ?: "sin conexion"})"
            }
        }

        return true
    }

    // CRUD READ: obtiene los contenidos visibles segun rol y filtros
    fun contenidosVisibles(
        rol: RolUsuario?,
        asignatura: String? = null,
        unidad: String? = null,
        objetivo: String? = null
    ): List<ContenidoUiState> {
        val base = when (rol) {
            // REGLA DE NEGOCIO: el estudiante solo puede ver contenidos aprobados
            RolUsuario.ESTUDIANTE -> _contenidos.value.filter { it.estado == EstadoContenido.APROBADO }
            RolUsuario.UTP, RolUsuario.DOCENTE -> _contenidos.value
            null -> emptyList()
        }

        return base.filter { contenido ->
            val coincideAsignatura = asignatura.isNullOrBlank() || contenido.asignatura.equals(asignatura, ignoreCase = true)
            val coincideUnidad = unidad.isNullOrBlank() || contenido.unidad.equals(unidad, ignoreCase = true)
            val coincideObjetivo = objetivo.isNullOrBlank() || contenido.objetivo.equals(objetivo, ignoreCase = true)
            coincideAsignatura && coincideUnidad && coincideObjetivo
        }
    }

    fun prepararNuevoContenido(asignatura: String, unidad: String, objetivo: String) {
        _uiState.value = ContenidoUiState(
            asignatura = asignatura,
            unidad = unidad,
            objetivo = objetivo
        )
        _errores.value = ContenidoErrores()
    }

    fun cargarContenidoParaEditar(contenido: ContenidoUiState) {
        _uiState.value = contenido
        _errores.value = ContenidoErrores()
    }

    // CRUD UPDATE: modifica un contenido existente y envia PUT /api/contenidos/{id}
    fun editarContenido(rol: RolUsuario?): Boolean {
        // REGLA DE NEGOCIO: solo UTP y Docente pueden editar contenidos
        if (rol != RolUsuario.UTP && rol != RolUsuario.DOCENTE) {
            return false
        }

        if (!validarFormulario()) {
            return false
        }

        if (rol == RolUsuario.DOCENTE && !_uiState.value.asignatura.equals("Lenguaje", ignoreCase = true)) {
            _errores.value = _errores.value.copy(
                asignatura = "El docente solo puede trabajar con Lenguaje"
            )
            return false
        }

        // REGLA DE NEGOCIO: si el Docente edita un contenido, vuelve a estado pendiente
        val contenidoEditado = if (rol == RolUsuario.DOCENTE) {
            _uiState.value.copy(estado = EstadoContenido.PENDIENTE)
        } else {
            _uiState.value
        }

        val nuevaLista = _contenidos.value.map { contenido ->
            if (contenido.id == contenidoEditado.id) contenidoEditado else contenido
        }

        guardarLista(nuevaLista)
        limpiarFormulario()

        viewModelScope.launch {
            try {
                val actualizadoRemoto = repository.actualizarContenido(contenidoEditado.id, contenidoEditado)
                val listaRemota = _contenidos.value.map { item ->
                    if (item.id == actualizadoRemoto.id) actualizadoRemoto else item
                }
                guardarLista(listaRemota)
                _mensajeSincronizacion.value = "Contenido actualizado en el servidor (PUT #${actualizadoRemoto.id})"
            } catch (e: Exception) {
                _mensajeSincronizacion.value =
                    "Actualizado localmente (PUT pendiente: ${e.message ?: "sin conexion"})"
            }
        }

        return true
    }

    fun aprobarContenido(id: Int, rol: RolUsuario?): Boolean {
        if (rol != RolUsuario.UTP) return false
        cambiarEstado(id, EstadoContenido.APROBADO)
        return true
    }

    fun rechazarContenido(id: Int, rol: RolUsuario?): Boolean {
        if (rol != RolUsuario.UTP) return false
        cambiarEstado(id, EstadoContenido.RECHAZADO)
        return true
    }

    fun ocultarContenido(id: Int, rol: RolUsuario?): Boolean {
        if (rol != RolUsuario.UTP) return false
        cambiarEstado(id, EstadoContenido.OCULTO)
        return true
    }

    // CRUD DELETE: elimina un contenido por ID localmente y envia DELETE /api/contenidos/{id}
    // REGLA DE NEGOCIO: solo UTP puede eliminar contenidos
    fun eliminarContenido(id: Int, rol: RolUsuario?) {
        if (rol != RolUsuario.UTP) return

        val nuevaLista = _contenidos.value.filterNot { contenido -> contenido.id == id }
        guardarLista(nuevaLista)

        viewModelScope.launch {
            try {
                val ok = repository.eliminarContenido(id)
                _mensajeSincronizacion.value = if (ok) {
                    "Contenido #$id eliminado del servidor (DELETE)"
                } else {
                    "El servidor no confirmo la eliminacion del contenido #$id"
                }
            } catch (e: Exception) {
                _mensajeSincronizacion.value =
                    "Eliminado localmente (DELETE pendiente: ${e.message ?: "sin conexion"})"
            }
        }
    }

    fun contenidosPendientes(): List<ContenidoUiState> {
        return _contenidos.value.filter { it.estado == EstadoContenido.PENDIENTE }
    }

    fun contenidosAprobados(): List<ContenidoUiState> {
        return _contenidos.value.filter { it.estado == EstadoContenido.APROBADO }
    }

    fun contenidosOcultos(): List<ContenidoUiState> {
        return _contenidos.value.filter { it.estado == EstadoContenido.OCULTO }
    }

    fun reactivarContenido(id: Int, rol: RolUsuario?) {
        if (rol != RolUsuario.UTP) return

        var reactivado: ContenidoUiState? = null
        val nuevaLista = _contenidos.value.map { contenido ->
            if (contenido.id == id && contenido.estado == EstadoContenido.OCULTO) {
                contenido.copy(estado = EstadoContenido.APROBADO).also { reactivado = it }
            } else {
                contenido
            }
        }
        guardarLista(nuevaLista)

        reactivado?.let { actualizado ->
            sincronizarActualizacionEstado(actualizado)
        }
    }

    fun contenidosRechazados(): List<ContenidoUiState> {
        return _contenidos.value.filter { it.estado == EstadoContenido.RECHAZADO }
    }

    fun puedeEditarContenido(contenido: ContenidoUiState, rol: RolUsuario?): Boolean {
        return when (rol) {
            RolUsuario.UTP -> true
            RolUsuario.DOCENTE -> contenido.asignatura.equals("Lenguaje", ignoreCase = true)
            else -> false
        }
    }

    fun contenidoPorId(id: Int): ContenidoUiState? {
        return _contenidos.value.find { it.id == id }
    }

    private fun cambiarEstado(id: Int, nuevoEstado: EstadoContenido) {
        var contenidoActualizado: ContenidoUiState? = null
        val nuevaLista = _contenidos.value.map { contenido ->
            if (contenido.id == id) {
                contenido.copy(estado = nuevoEstado).also { contenidoActualizado = it }
            } else {
                contenido
            }
        }

        guardarLista(nuevaLista)

        contenidoActualizado?.let { actualizado ->
            sincronizarActualizacionEstado(actualizado)
        }
    }

    private fun sincronizarActualizacionEstado(actualizado: ContenidoUiState) {
        viewModelScope.launch {
            try {
                repository.actualizarContenido(actualizado.id, actualizado)
                _mensajeSincronizacion.value = "Estado actualizado en el servidor (${actualizado.estado.name})"
            } catch (e: Exception) {
                _mensajeSincronizacion.value =
                    "Estado guardado localmente (PUT pendiente: ${e.message ?: "sin conexion"})"
            }
        }
    }

    // DATASTORE: actualiza la lista en memoria y la guarda de forma persistente
    private fun guardarLista(nuevaLista: List<ContenidoUiState>) {
        _contenidos.value = nuevaLista
        viewModelScope.launch {
            localStorage.guardarContenidos(nuevaLista)
        }
    }

    private fun limpiarFormulario() {
        _uiState.value = ContenidoUiState()
        _errores.value = ContenidoErrores()
    }
}
