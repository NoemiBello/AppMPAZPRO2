package com.example.appmpazpro2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmpazpro2.data.ContenidoDataStore
import com.example.appmpazpro2.model.ContenidoErrores
import com.example.appmpazpro2.model.ContenidoUiState
import com.example.appmpazpro2.model.EstadoContenido
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContenidoViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val dataStore = ContenidoDataStore(application)

    private val _uiState = MutableStateFlow(ContenidoUiState())

    val uiState: StateFlow<ContenidoUiState> = _uiState.asStateFlow()

    private val _errores = MutableStateFlow(ContenidoErrores())

    val errores: StateFlow<ContenidoErrores> = _errores.asStateFlow()

    private val _contenidos = MutableStateFlow<List<ContenidoUiState>>(
        emptyList()
    )

    val contenidos: StateFlow<List<ContenidoUiState>> = _contenidos.asStateFlow()

    init {

        viewModelScope.launch {

            dataStore.obtenerContenidos().collect { lista ->

                    _contenidos.value = lista
                }
        }
    }

    fun actualizarAsignatura(valor: String) {

        _uiState.value = _uiState.value.copy(
            asignatura = valor
        )

        _errores.value = _errores.value.copy(
            asignatura = null
        )
    }

    fun actualizarUnidad(valor: String) {

        _uiState.value = _uiState.value.copy(
            unidad = valor
        )

        _errores.value = _errores.value.copy(
            unidad = null
        )
    }

    fun actualizarObjetivo(valor: String) {

        _uiState.value = _uiState.value.copy(
            objetivo = valor
        )

        _errores.value = _errores.value.copy(
            objetivo = null
        )
    }

    fun actualizarTipo(valor: String) {

        _uiState.value = _uiState.value.copy(
            tipo = valor
        )

        _errores.value = _errores.value.copy(
            tipo = null
        )
    }

    fun actualizarTitulo(valor: String) {

        _uiState.value = _uiState.value.copy(
            titulo = valor
        )

        _errores.value = _errores.value.copy(
            titulo = null
        )
    }

    fun actualizarContenido(valor: String) {

        _uiState.value = _uiState.value.copy(
            contenido = valor
        )

        _errores.value = _errores.value.copy(
            contenido = null
        )
    }

    fun actualizarPregunta(valor: String) {

        _uiState.value = _uiState.value.copy(
            pregunta = valor
        )

        _errores.value = _errores.value.copy(
            pregunta = null
        )
    }

    fun validarFormulario(): Boolean {

        val estadoActual = _uiState.value

        val nuevosErrores = ContenidoErrores(

            asignatura = if (estadoActual.asignatura.isBlank()) {
                "La asignatura es obligatoria"
            } else {
                null
            },

            unidad = if (estadoActual.unidad.isBlank()) {
                "La unidad es obligatoria"
            } else {
                null
            },

            objetivo = if (estadoActual.objetivo.isBlank()) {
                "El objetivo es obligatorio"
            } else {
                null
            },

            tipo = if (estadoActual.tipo.isBlank()) {
                "El tipo es obligatorio"
            } else {
                null
            },

            titulo = if (estadoActual.titulo.isBlank()) {
                "El titulo es obligatorio"
            } else {
                null
            },
            contenido = if (estadoActual.contenido.isBlank()) {
                "El contenido es obligatorio"
            } else {
                null
            },

            pregunta = if (estadoActual.pregunta.isBlank()) {
                "La pregunta es obligatoria"
            } else {
                null
            }
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

    fun guardarContenido(
        rol: RolUsuario?
    ): Boolean {

        if (rol != RolUsuario.UTP && rol != RolUsuario.DOCENTE) {
            return false
        }

        if (!validarFormulario()) {
            return false
        }

        if (rol == RolUsuario.DOCENTE && !_uiState.value.asignatura.equals(
                "Lenguaje", ignoreCase = true
            )
        ) {

            _errores.value = _errores.value.copy(
                asignatura = "El docente solo puede trabajar con Lenguaje"
            )

            return false
        }

        val nuevoId = (_contenidos.value.maxOfOrNull {
            it.id
        } ?: 0) + 1

        val estadoInicial = if (rol == RolUsuario.UTP) {
            EstadoContenido.APROBADO
        } else {
            EstadoContenido.PENDIENTE
        }


        val nuevoContenido = _uiState.value.copy(
            id = nuevoId, estado = if (rol == RolUsuario.UTP) {
                EstadoContenido.APROBADO
            } else {
                EstadoContenido.PENDIENTE
            }
        )

        val nuevaLista = _contenidos.value + nuevoContenido

        guardarLista(nuevaLista)

        limpiarFormulario()

        return true
    }


    fun contenidosVisibles(
        rol: RolUsuario?,
        asignatura: String? = null,
        unidad: String? = null,
        objetivo: String? = null
    ): List<ContenidoUiState> {

        val base = when (rol) {

            RolUsuario.ESTUDIANTE -> _contenidos.value.filter {
                it.estado == EstadoContenido.APROBADO
            }

            RolUsuario.UTP, RolUsuario.DOCENTE -> _contenidos.value

            null -> emptyList()
        }

        return base.filter { contenido ->

            val coincideAsignatura = asignatura.isNullOrBlank() || contenido.asignatura.equals(
                asignatura, ignoreCase = true
            )

            val coincideUnidad = unidad.isNullOrBlank() || contenido.unidad.equals(
                unidad, ignoreCase = true
            )

            val coincideObjetivo = objetivo.isNullOrBlank() || contenido.objetivo.equals(
                objetivo, ignoreCase = true
            )

            coincideAsignatura && coincideUnidad && coincideObjetivo
        }
    }

    fun prepararNuevoContenido(
        asignatura: String, unidad: String, objetivo: String
    ) {

        _uiState.value = ContenidoUiState(
            asignatura = asignatura, unidad = unidad, objetivo = objetivo
        )

        _errores.value = ContenidoErrores()
    }

    fun cargarContenidoParaEditar(
        contenido: ContenidoUiState
    ) {

        _uiState.value = contenido

        _errores.value = ContenidoErrores()
    }

    fun editarContenido(
        rol: RolUsuario?
    ): Boolean {

        if (rol != RolUsuario.UTP && rol != RolUsuario.DOCENTE) {
            return false
        }

        if (!validarFormulario()) {
            return false
        }


        if (rol == RolUsuario.DOCENTE && !_uiState.value.asignatura.equals(
                "Lenguaje", ignoreCase = true
            )
        ) {

            _errores.value = _errores.value.copy(
                asignatura = "El docente solo puede trabajar con Lenguaje"
            )

            return false
        }

        val contenidoEditado = if (rol == RolUsuario.DOCENTE) {

            _uiState.value.copy(
                estado = EstadoContenido.PENDIENTE
            )

        } else {

            _uiState.value
        }

        val nuevaLista = _contenidos.value.map { contenido ->

            if (contenido.id == contenidoEditado.id) {

                contenidoEditado

            } else {

                contenido
            }
        }

        guardarLista(nuevaLista)

        limpiarFormulario()

        return true
    }

    fun aprobarContenido(
        id: Int, rol: RolUsuario?
    ): Boolean {

        if (rol != RolUsuario.UTP) {
            return false
        }

        cambiarEstado(
            id, EstadoContenido.APROBADO
        )

        return true
    }

    fun rechazarContenido(
        id: Int, rol: RolUsuario?
    ): Boolean {

        if (rol != RolUsuario.UTP) {
            return false
        }

        cambiarEstado(
            id, EstadoContenido.RECHAZADO
        )

        return true
    }

    fun ocultarContenido(
        id: Int, rol: RolUsuario?
    ): Boolean {

        if (rol != RolUsuario.UTP) {
            return false
        }

        cambiarEstado(
            id, EstadoContenido.OCULTO
        )

        return true
    }

    fun eliminarContenido(
        id: Int,
        rol: RolUsuario?
    ) {
        if (rol != RolUsuario.UTP) return

        _contenidos.value = _contenidos.value.filterNot { contenido ->
            contenido.id == id
        }
    }

    fun contenidosPendientes(): List<ContenidoUiState> {

        return _contenidos.value.filter {

            it.estado == EstadoContenido.PENDIENTE
        }
    }

    fun contenidosAprobados(): List<ContenidoUiState> {

        return _contenidos.value.filter {

            it.estado == EstadoContenido.APROBADO
        }
    }

    fun contenidosOcultos(): List<ContenidoUiState> {

        return _contenidos.value.filter {

            it.estado == EstadoContenido.OCULTO
        }
    }

    fun contenidosRechazados(): List<ContenidoUiState> {

        return _contenidos.value.filter {

            it.estado == EstadoContenido.RECHAZADO
        }
    }

    fun puedeEditarContenido(
        contenido: ContenidoUiState, rol: RolUsuario?
    ): Boolean {

        return when (rol) {

            RolUsuario.UTP -> true

            RolUsuario.DOCENTE -> contenido.asignatura.equals(
                "Lenguaje", ignoreCase = true
            )

            else -> false
        }
    }

    fun contenidoPorId(
        id: Int
    ): ContenidoUiState? {

        return _contenidos.value.find {
            it.id == id
        }
    }

    private fun cambiarEstado(
        id: Int, nuevoEstado: EstadoContenido
    ) {

        val nuevaLista = _contenidos.value.map { contenido ->

            if (contenido.id == id) {

                contenido.copy(
                    estado = nuevoEstado
                )

            } else {

                contenido
            }
        }

        guardarLista(nuevaLista)
    }

    private fun guardarLista(
        nuevaLista: List<ContenidoUiState>
    ) {

        _contenidos.value = nuevaLista

        viewModelScope.launch {

            dataStore.guardarContenidos(
                nuevaLista
            )
        }
    }

    private fun limpiarFormulario() {

        _uiState.value = ContenidoUiState()

        _errores.value = ContenidoErrores()
    }
}