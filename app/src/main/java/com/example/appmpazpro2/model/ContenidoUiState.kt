package com.example.appmpazpro2.model

data class ContenidoUiState(
    val id: Int = 0,
    val asignatura: String = "",
    val unidad: String = "",
    val objetivo: String = "",
    val tipo: String = "",
    val titulo: String = "",
    val contenido: String = "",
    val estado: EstadoContenido = EstadoContenido.PENDIENTE
)