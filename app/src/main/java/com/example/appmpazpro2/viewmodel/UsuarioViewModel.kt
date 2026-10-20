package com.example.appmpazpro2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appmpazpro2.model.UsuarioErrores
import com.example.appmpazpro2.model.UsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsuarioViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UsuarioUiState())

    val uiState: StateFlow<UsuarioUiState> =
        _uiState.asStateFlow()

    fun actualizarNombre(nombre: String) {
        _uiState.value = _uiState.value.copy(
            nombre = nombre
        )
    }

    fun actualizarCorreo(correo: String) {
        _uiState.value = _uiState.value.copy(
            correo = correo
        )
    }

    fun actualizarClave(clave: String) {
        _uiState.value = _uiState.value.copy(
            clave = clave
        )
    }

    fun actualizarDireccion(direccion: String) {
        _uiState.value = _uiState.value.copy(
            direccion = direccion
        )
    }

    fun actualizarTerminos(aceptaTerminos: Boolean) {
        _uiState.value = _uiState.value.copy(
            aceptaTerminos = aceptaTerminos
        )
    }

    fun validarFormulario(): Boolean {

        val estado = _uiState.value

        val errores = UsuarioErrores(
            nombre = if (estado.nombre.isBlank()) {
                "El nombre es obligatorio"
            } else {
                null
            },

            correo = if (
                estado.correo.isBlank() ||
                !estado.correo.contains("@")
            ) {
                "Ingrese un correo valido"
            } else {
                null
            },

            clave = if (estado.clave.length < 6) {
                "La clave debe tener al menos 6 caracteres"
            } else {
                null
            },

            direccion = if (estado.direccion.isBlank()) {
                "La direccion es obligatoria"
            } else {
                null
            }
        )

        _uiState.value = estado.copy(
            errores = errores
        )

        val camposValidos =
            errores.nombre == null &&
                    errores.correo == null &&
                    errores.clave == null &&
                    errores.direccion == null

        return camposValidos && estado.aceptaTerminos
    }
}