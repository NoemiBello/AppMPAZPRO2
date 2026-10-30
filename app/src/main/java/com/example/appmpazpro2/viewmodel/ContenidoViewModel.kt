package com.example.appmpazpro2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmpazpro2.data.ContenidoDataStore
import com.example.appmpazpro2.model.ContenidoErrores
import com.example.appmpazpro2.model.ContenidoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContenidoViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val dataStore =
        ContenidoDataStore(application)

    private val _uiState =
        MutableStateFlow(ContenidoUiState())

    val uiState: StateFlow<ContenidoUiState> =
        _uiState.asStateFlow()

    private val _errores =
        MutableStateFlow(ContenidoErrores())

    val errores: StateFlow<ContenidoErrores> =
        _errores.asStateFlow()

    private val _contenidos =
        MutableStateFlow<List<ContenidoUiState>>(
            emptyList()
        )

    val contenidos: StateFlow<List<ContenidoUiState>> =
        _contenidos.asStateFlow()

    init {

        viewModelScope.launch {

            dataStore.obtenerContenidos()
                .collect { lista ->

                    _contenidos.value = lista
                }
        }
    }

    fun actualizarAsignatura(valor: String) {

        _uiState.value =
            _uiState.value.copy(
                asignatura = valor
            )

        _errores.value =
            _errores.value.copy(
                asignatura = null
            )
    }

    fun actualizarUnidad(valor: String) {

        _uiState.value =
            _uiState.value.copy(
                unidad = valor
            )

        _errores.value =
            _errores.value.copy(
                unidad = null
            )
    }

    fun actualizarObjetivo(valor: String) {

        _uiState.value =
            _uiState.value.copy(
                objetivo = valor
            )

        _errores.value =
            _errores.value.copy(
                objetivo = null
            )
    }

    fun actualizarTipo(valor: String) {

        _uiState.value =
            _uiState.value.copy(
                tipo = valor
            )

        _errores.value =
            _errores.value.copy(
                tipo = null
            )
    }

    fun actualizarTitulo(valor: String) {

        _uiState.value =
            _uiState.value.copy(
                titulo = valor
            )

        _errores.value =
            _errores.value.copy(
                titulo = null
            )
    }

    fun actualizarContenido(valor: String) {

        _uiState.value =
            _uiState.value.copy(
                contenido = valor
            )

        _errores.value =
            _errores.value.copy(
                contenido = null
            )
    }

    fun validarFormulario(): Boolean {

        val estado = _uiState.value

        val nuevosErrores =
            ContenidoErrores(

                asignatura =
                    if (estado.asignatura.isBlank()) {
                        "La asignatura es obligatoria"
                    } else {
                        null
                    },

                unidad =
                    if (estado.unidad.isBlank()) {
                        "La unidad es obligatoria"
                    } else {
                        null
                    },

                objetivo =
                    if (estado.objetivo.isBlank()) {
                        "El objetivo es obligatorio"
                    } else {
                        null
                    },

                tipo =
                    if (estado.tipo.isBlank()) {
                        "El tipo es obligatorio"
                    } else {
                        null
                    },

                titulo =
                    if (estado.titulo.isBlank()) {
                        "El titulo es obligatorio"
                    } else {
                        null
                    },

                contenido =
                    if (estado.contenido.isBlank()) {
                        "El contenido es obligatorio"
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
                nuevosErrores.contenido == null
    }

    fun guardarContenido(
        rol: RolUsuario?
    ): Boolean {

        if (
            rol != RolUsuario.UTP &&
            rol != RolUsuario.DOCENTE
        ) {
            return false
        }

        if (!validarFormulario()) {
            return false
        }

        val nuevoContenido =
            _uiState.value

        val nuevaLista =
            _contenidos.value +
                    nuevoContenido

        _contenidos.value =
            nuevaLista

        viewModelScope.launch {

            dataStore.guardarContenidos(
                nuevaLista
            )
        }

        _uiState.value =
            ContenidoUiState()

        _errores.value =
            ContenidoErrores()

        return true
    }

    fun contenidosVisibles(
        rol: RolUsuario?,
        asignatura: String? = null
    ): List<ContenidoUiState> {

        val base =
            when (rol) {

                RolUsuario.ESTUDIANTE ->
                    _contenidos.value.filter {
                        it.titulo.isNotBlank() &&
                                it.contenido.isNotBlank()
                    }

                RolUsuario.UTP,
                RolUsuario.DOCENTE ->
                    _contenidos.value

                null ->
                    emptyList()
            }

        return if (
            asignatura.isNullOrBlank()
        ) {

            base

        } else {

            base.filter {
                it.asignatura.equals(
                    asignatura,
                    ignoreCase = true
                )
            }
        }
    }
}