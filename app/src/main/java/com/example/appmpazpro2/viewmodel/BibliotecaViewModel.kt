package com.example.appmpazpro2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmpazpro2.data.model.LibroRecomendado
import com.example.appmpazpro2.repository.BibliotecaRepository
import com.example.appmpazpro2.repository.BibliotecaRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class BibliotecaViewModel(
    private val repository: BibliotecaRepository = BibliotecaRepositoryImpl(),
    cargarAlIniciar: Boolean = true
) : ViewModel() {

    protected val _libros = MutableStateFlow<List<LibroRecomendado>>(emptyList())
    open val libros: StateFlow<List<LibroRecomendado>> = _libros.asStateFlow()

    protected val _cargando = MutableStateFlow(false)
    open val cargando: StateFlow<Boolean> = _cargando.asStateFlow()

    protected val _error = MutableStateFlow<String?>(null)
    open val error: StateFlow<String?> = _error.asStateFlow()

    protected val _consulta = MutableStateFlow("")
    open val consulta: StateFlow<String> = _consulta.asStateFlow()

    init {
        if (cargarAlIniciar) {
            cargarLibrosRecomendados()
        }
    }

    fun actualizarConsulta(valor: String) {
        _consulta.value = valor
    }

    open fun cargarLibrosRecomendados() {
        viewModelScope.launch {
            _cargando.value = true
            _error.value = null
            try {
                _libros.value = repository.obtenerLibrosRecomendados()
            } catch (e: Exception) {
                _error.value = "No pudimos cargar los libros: ${e.message ?: "revisa tu conexion"}"
            } finally {
                _cargando.value = false
            }
        }
    }

    open fun buscarLibros() {
        val texto = _consulta.value.trim()
        viewModelScope.launch {
            _cargando.value = true
            _error.value = null
            try {
                _libros.value = if (texto.isBlank()) {
                    repository.obtenerLibrosRecomendados()
                } else {
                    repository.buscarLibros(texto)
                }
            } catch (e: Exception) {
                _error.value = "Error al buscar libros: ${e.message ?: "revisa tu conexion"}"
            } finally {
                _cargando.value = false
            }
        }
    }

    fun limpiarBusqueda() {
        _consulta.value = ""
        cargarLibrosRecomendados()
    }
}
