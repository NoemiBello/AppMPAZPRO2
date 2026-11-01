package com.example.appmpazpro2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmpazpro2.data.ContenidoDataStore
import com.example.appmpazpro2.model.EstadoRespuesta
import com.example.appmpazpro2.model.RespuestaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProgresoViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val dataStore =
        ContenidoDataStore(application)

    private val _respuestaActual =
        MutableStateFlow("")

    val respuestaActual: StateFlow<String> =
        _respuestaActual.asStateFlow()

    private val _respuestas =
        MutableStateFlow<List<RespuestaUiState>>(
            emptyList()
        )

    val respuestas: StateFlow<List<RespuestaUiState>> =
        _respuestas.asStateFlow()

    private val _progreso =
        MutableStateFlow(0f)

    val progreso: StateFlow<Float> =
        _progreso.asStateFlow()

    private val _porcentaje =
        MutableStateFlow(0)

    val porcentaje: StateFlow<Int> =
        _porcentaje.asStateFlow()

    init {

        viewModelScope.launch {

            dataStore.obtenerRespuestas()
                .collect { lista ->

                    _respuestas.value = lista

                    calcularProgreso()
                }
        }
    }

    fun actualizarRespuesta(
        valor: String
    ) {

        _respuestaActual.value = valor
    }

    fun registrarRespuesta(
        contenidoId: Int
    ): Boolean {

        if (
            _respuestaActual.value.isBlank()
        ) {
            return false
        }

        val nuevoId =
            (_respuestas.value.maxOfOrNull {
                it.id
            } ?: 0) + 1

        val nuevaRespuesta =
            RespuestaUiState(
                id = nuevoId,
                contenidoId = contenidoId,
                respuesta = _respuestaActual.value,
                estado =
                    EstadoRespuesta.PENDIENTE_REVISION
            )

        val nuevaLista =
            _respuestas.value +
                    nuevaRespuesta

        guardarRespuestas(
            nuevaLista
        )

        _respuestaActual.value = ""

        return true
    }

    fun marcarCorrecta(
        respuestaId: Int,
        rol: RolUsuario?
    ): Boolean {

        if (
            rol != RolUsuario.UTP &&
            rol != RolUsuario.DOCENTE
        ) {
            return false
        }

        cambiarEstadoRespuesta(
            respuestaId,
            EstadoRespuesta.CORRECTA
        )

        return true
    }

    fun marcarIncorrecta(
        respuestaId: Int,
        rol: RolUsuario?
    ): Boolean {

        if (
            rol != RolUsuario.UTP &&
            rol != RolUsuario.DOCENTE
        ) {
            return false
        }

        cambiarEstadoRespuesta(
            respuestaId,
            EstadoRespuesta.INCORRECTA
        )

        return true
    }

    fun respuestasPendientes():
            List<RespuestaUiState> {

        return _respuestas.value.filter {

            it.estado ==
                    EstadoRespuesta.PENDIENTE_REVISION
        }
    }

    fun respuestasDeContenido(
        contenidoId: Int
    ): List<RespuestaUiState> {

        return _respuestas.value.filter {

            it.contenidoId ==
                    contenidoId
        }
    }

    fun obtenerRespuestasPendientes():
            List<RespuestaUiState> {

        return _respuestas.value.filter {

            it.estado ==
                    EstadoRespuesta.PENDIENTE_REVISION
        }
    }


    private fun cambiarEstadoRespuesta(
        respuestaId: Int,
        nuevoEstado: EstadoRespuesta
    ) {

        val nuevaLista =
            _respuestas.value.map { respuesta ->

                if (
                    respuesta.id ==
                    respuestaId
                ) {

                    respuesta.copy(
                        estado = nuevoEstado
                    )

                } else {

                    respuesta
                }
            }

        guardarRespuestas(
            nuevaLista
        )
    }

    private fun guardarRespuestas(
        nuevaLista:
        List<RespuestaUiState>
    ) {

        _respuestas.value =
            nuevaLista

        calcularProgreso()

        viewModelScope.launch {

            dataStore.guardarRespuestas(
                nuevaLista
            )
        }
    }

    private fun calcularProgreso() {

        val total =
            _respuestas.value.size

        if (total == 0) {

            _porcentaje.value = 0
            _progreso.value = 0f

            return
        }

        val correctas =
            _respuestas.value.count {

                it.estado ==
                        EstadoRespuesta.CORRECTA
            }

        val porcentajeCalculado =
            (
                    correctas.toFloat() /
                            total.toFloat() *
                            100
                    ).toInt()

        _porcentaje.value =
            porcentajeCalculado

        _progreso.value =
            porcentajeCalculado / 100f
    }
}