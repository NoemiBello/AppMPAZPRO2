package com.example.appmpazpro2.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProgresoViewModel : ViewModel() {

    private val _respuestaActual =
        MutableStateFlow("")

    val respuestaActual: StateFlow<String> =
        _respuestaActual.asStateFlow()

    private val _respuestasRegistradas =
        MutableStateFlow(0)

    val respuestasRegistradas: StateFlow<Int> =
        _respuestasRegistradas.asStateFlow()

    private val _progreso =
        MutableStateFlow(0f)

    val progreso: StateFlow<Float> =
        _progreso.asStateFlow()

    private val _porcentaje =
        MutableStateFlow(0)

    val porcentaje: StateFlow<Int> =
        _porcentaje.asStateFlow()

    fun actualizarRespuesta(
        valor: String
    ) {

        _respuestaActual.value = valor
    }

    fun registrarRespuesta(): Boolean {

        if (
            _respuestaActual.value.isBlank()
        ) {
            return false
        }

        _respuestasRegistradas.value += 1

        _porcentaje.value =
            (
                    _respuestasRegistradas.value * 20
                    ).coerceAtMost(100)

        _progreso.value =
            _porcentaje.value / 100f

        _respuestaActual.value = ""

        return true
    }

    fun reiniciarProgreso() {

        _respuestasRegistradas.value = 0
        _porcentaje.value = 0
        _progreso.value = 0f
        _respuestaActual.value = ""
    }
}