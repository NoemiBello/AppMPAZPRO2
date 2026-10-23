package com.example.appmpazpro2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmpazpro2.data.EstadoDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EstadoViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val estadoDataStore = EstadoDataStore(application)

    private val _activo = MutableStateFlow<Boolean?>(null)
    val activo: StateFlow<Boolean?> = _activo

    private val _mostrarMensaje = MutableStateFlow(false)
    val mostrarMensaje: StateFlow<Boolean> = _mostrarMensaje

    init {
        cargarEstado()
    }

    private fun cargarEstado() {
        viewModelScope.launch {

            delay(1500)

            _activo.value =
                estadoDataStore.obtenerEstado().first() ?: false
        }
    }

    fun alternarEstado() {
        viewModelScope.launch {

            val nuevoEstado = !(_activo.value ?: false)

            estadoDataStore.guardarEstado(nuevoEstado)

            _activo.value = nuevoEstado

            _mostrarMensaje.value = true

            delay(2000)

            _mostrarMensaje.value = false
        }
    }
}