package com.example.appmpazpro2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmpazpro2.data.ContenidoDataStore
import com.example.appmpazpro2.data.ContenidoLocalStorage
import com.example.appmpazpro2.model.EstadoRespuesta
import com.example.appmpazpro2.model.RespuestaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProgresoViewModel(
    application: Application,
    private val localStorage: ContenidoLocalStorage,
    cargarAlIniciar: Boolean = true
) : AndroidViewModel(application) {

    constructor(application: Application) : this(
        application = application,
        localStorage = ContenidoDataStore(application),
        cargarAlIniciar = true
    )

    private val _respuestaActual = MutableStateFlow("")
    val respuestaActual: StateFlow<String> = _respuestaActual.asStateFlow()

    private val _respuestas = MutableStateFlow<List<RespuestaUiState>>(emptyList())
    val respuestas: StateFlow<List<RespuestaUiState>> = _respuestas.asStateFlow()

    private val _progreso = MutableStateFlow(0f)
    val progreso: StateFlow<Float> = _progreso.asStateFlow()

    private val _porcentaje = MutableStateFlow(0)
    val porcentaje: StateFlow<Int> = _porcentaje.asStateFlow()

    init {
        if (cargarAlIniciar) {
            viewModelScope.launch {
                localStorage.obtenerRespuestas().collect { lista ->
                    _respuestas.value = lista
                    calcularProgreso()
                }
            }
        }
    }

    fun actualizarRespuesta(valor: String) {
        _respuestaActual.value = valor
    }

    fun registrarRespuesta(contenidoId: Int): Boolean {
        // REGLA DE NEGOCIO: no se puede registrar una respuesta vacia
        if (_respuestaActual.value.isBlank()) {
            return false
        }

        // REGLA DE NEGOCIO: el estudiante solo puede responder si aun tiene intentos disponibles
        if (!puedeResponder(contenidoId)) {
            return false
        }

        val nuevoId = (_respuestas.value.maxOfOrNull { it.id } ?: 0) + 1

        val nuevaRespuesta = RespuestaUiState(
            id = nuevoId,
            contenidoId = contenidoId,
            respuesta = _respuestaActual.value,
            estado = EstadoRespuesta.PENDIENTE_REVISION
        )

        guardarRespuestas(_respuestas.value + nuevaRespuesta)
        _respuestaActual.value = ""
        return true
    }

    // REGLA DE NEGOCIO: solo UTP y Docente pueden marcar una respuesta como correcta
    fun marcarCorrecta(respuestaId: Int, rol: RolUsuario?): Boolean {
        if (rol != RolUsuario.UTP && rol != RolUsuario.DOCENTE) {
            return false
        }
        cambiarEstadoRespuesta(respuestaId, EstadoRespuesta.CORRECTA)
        return true
    }

    // REGLA DE NEGOCIO: solo UTP y Docente pueden marcar una respuesta como incorrecta
    fun marcarIncorrecta(respuestaId: Int, rol: RolUsuario?): Boolean {
        if (rol != RolUsuario.UTP && rol != RolUsuario.DOCENTE) {
            return false
        }
        cambiarEstadoRespuesta(respuestaId, EstadoRespuesta.INCORRECTA)
        return true
    }

    fun respuestasPendientes(): List<RespuestaUiState> {
        return _respuestas.value.filter { it.estado == EstadoRespuesta.PENDIENTE_REVISION }
    }

    fun respuestasDeContenido(contenidoId: Int): List<RespuestaUiState> {
        return _respuestas.value.filter { it.contenidoId == contenidoId }
    }

    fun obtenerRespuestasPendientes(): List<RespuestaUiState> {
        return _respuestas.value.filter { it.estado == EstadoRespuesta.PENDIENTE_REVISION }
    }

    private fun cambiarEstadoRespuesta(respuestaId: Int, nuevoEstado: EstadoRespuesta) {
        val nuevaLista = _respuestas.value.map { respuesta ->
            if (respuesta.id == respuestaId) {
                respuesta.copy(estado = nuevoEstado)
            } else {
                respuesta
            }
        }
        guardarRespuestas(nuevaLista)
    }

    private fun guardarRespuestas(nuevaLista: List<RespuestaUiState>) {
        _respuestas.value = nuevaLista
        calcularProgreso()
        viewModelScope.launch {
            localStorage.guardarRespuestas(nuevaLista)
        }
    }

    private fun calcularProgreso() {
        val total = _respuestas.value.size
        if (total == 0) {
            _porcentaje.value = 0
            _progreso.value = 0f
            return
        }
        val correctas = _respuestas.value.count { it.estado == EstadoRespuesta.CORRECTA }
        val porcentajeCalculado = (correctas.toFloat() / total.toFloat() * 100).toInt()
        _porcentaje.value = porcentajeCalculado
        _progreso.value = porcentajeCalculado / 100f
    }

    companion object {
        // REGLA DE NEGOCIO: maximo 3 intentos por actividad
        const val MAX_INTENTOS = 3
    }

    fun intentosUsados(contenidoId: Int): Int {
        return _respuestas.value.count { it.contenidoId == contenidoId }
    }

    fun intentosRestantes(contenidoId: Int): Int {
        return (MAX_INTENTOS - intentosUsados(contenidoId)).coerceAtLeast(0)
    }

    // REGLA DE NEGOCIO: verifica que no se superen los 3 intentos permitidos
    fun puedeResponder(contenidoId: Int): Boolean {
        return intentosUsados(contenidoId) < MAX_INTENTOS
    }
}
