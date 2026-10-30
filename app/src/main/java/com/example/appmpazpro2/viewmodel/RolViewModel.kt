package com.example.appmpazpro2.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class RolUsuario {
    UTP,
    DOCENTE,
    ESTUDIANTE
}

class RolViewModel : ViewModel() {

    private val _rolSeleccionado = MutableStateFlow<RolUsuario?>(null)

    val rolSeleccionado: StateFlow<RolUsuario?> =
        _rolSeleccionado.asStateFlow()

    fun seleccionarRol(rol: RolUsuario) {
        _rolSeleccionado.value = rol
    }

    fun limpiarRol() {
        _rolSeleccionado.value = null
    }

    fun puedeGestionarContenido(): Boolean {
        return _rolSeleccionado.value == RolUsuario.UTP ||
                _rolSeleccionado.value == RolUsuario.DOCENTE
    }

    fun puedeResponderContenido(): Boolean {
        return _rolSeleccionado.value == RolUsuario.ESTUDIANTE
    }
}