package com.example.appmpazpro2.repository

import com.example.appmpazpro2.data.model.toDto
import com.example.appmpazpro2.data.model.toUiState
import com.example.appmpazpro2.data.remote.BackendApiService
import com.example.appmpazpro2.data.remote.BackendRetrofitInstance
import com.example.appmpazpro2.model.ContenidoUiState

interface ContenidoRepository {
    suspend fun obtenerContenidos(): List<ContenidoUiState>
    suspend fun obtenerPorId(id: Int): ContenidoUiState
    suspend fun crearContenido(contenido: ContenidoUiState): ContenidoUiState
    suspend fun actualizarContenido(id: Int, contenido: ContenidoUiState): ContenidoUiState
    suspend fun eliminarContenido(id: Int): Boolean
}

open class ContenidoRepositoryImpl(
    private val api: BackendApiService = BackendRetrofitInstance.api
) : ContenidoRepository {

    override suspend fun obtenerContenidos(): List<ContenidoUiState> {
        return api.listarContenidos().map { it.toUiState() }
    }

    override suspend fun obtenerPorId(id: Int): ContenidoUiState {
        return api.buscarContenidoPorId(id.toLong()).toUiState()
    }

    override suspend fun crearContenido(contenido: ContenidoUiState): ContenidoUiState {
        return api.crearContenido(contenido.toDto(incluirId = false)).toUiState()
    }

    override suspend fun actualizarContenido(
        id: Int,
        contenido: ContenidoUiState
    ): ContenidoUiState {
        return api.actualizarContenido(id.toLong(), contenido.toDto(incluirId = true)).toUiState()
    }

    override suspend fun eliminarContenido(id: Int): Boolean {
        val response = api.eliminarContenido(id.toLong())
        return response.isSuccessful
    }
}
