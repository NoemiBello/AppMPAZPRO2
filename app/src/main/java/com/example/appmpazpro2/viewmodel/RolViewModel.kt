package com.example.appmpazpro2.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class RolUsuario {
    UTP, DOCENTE, ESTUDIANTE
}

class RolViewModel : ViewModel() {

    private val _rolSeleccionado = MutableStateFlow<RolUsuario?>(null)

    val rolSeleccionado: StateFlow<RolUsuario?> = _rolSeleccionado.asStateFlow()

    // Para este MVP:
    // el docente trabaja con Lenguaje
    // y el estudiante tiene Lenguaje asignado.
    val asignaturaDocente = "Lenguaje"
    val asignaturaEstudiante = "Lenguaje"

    fun seleccionarRol(
        rol: RolUsuario
    ) {
        _rolSeleccionado.value = rol
    }

    fun limpiarRol() {
        _rolSeleccionado.value = null
    }

    fun puedeGestionarContenido(): Boolean {

        return _rolSeleccionado.value == RolUsuario.UTP || _rolSeleccionado.value == RolUsuario.DOCENTE
    }

    fun puedeRevisarContenido(): Boolean {

        return _rolSeleccionado.value == RolUsuario.UTP
    }

    fun puedeRevisarRespuestas(): Boolean {

        return _rolSeleccionado.value == RolUsuario.DOCENTE
    }

    fun puedeResponderContenido(): Boolean {

        return _rolSeleccionado.value == RolUsuario.ESTUDIANTE
    }

    fun puedeAccederAsignatura(
        asignatura: String
    ): Boolean {

        return when (_rolSeleccionado.value) {

            RolUsuario.UTP -> true

            RolUsuario.DOCENTE -> asignatura.equals(
                asignaturaDocente, ignoreCase = true
            )

            RolUsuario.ESTUDIANTE -> asignatura.equals(
                asignaturaEstudiante, ignoreCase = true
            )

            null -> false
        }
    }
}