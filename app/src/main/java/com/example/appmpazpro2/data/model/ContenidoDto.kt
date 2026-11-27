package com.example.appmpazpro2.data.model

import com.example.appmpazpro2.model.ContenidoUiState
import com.example.appmpazpro2.model.EstadoContenido

data class ContenidoDto(
    val id: Long? = null,
    val asignatura: String = "",
    val unidad: String = "",
    val objetivo: String = "",
    val tipo: String = "",
    val titulo: String = "",
    val contenido: String = "",
    val pregunta: String? = null,
    val imagenUri: String? = null,
    val estado: String = EstadoContenido.PENDIENTE.name
)

fun ContenidoDto.toUiState(): ContenidoUiState {
    val estadoEnum = runCatching {
        EstadoContenido.valueOf(estado.uppercase())
    }.getOrDefault(EstadoContenido.PENDIENTE)

    return ContenidoUiState(
        id = (id ?: 0L).toInt(),
        asignatura = asignatura,
        unidad = unidad,
        objetivo = objetivo,
        tipo = tipo,
        titulo = titulo,
        contenido = contenido,
        pregunta = pregunta.orEmpty(),
        imagenUri = imagenUri?.ifBlank { null },
        estado = estadoEnum
    )
}

fun ContenidoUiState.toDto(incluirId: Boolean = true): ContenidoDto {
    return ContenidoDto(
        id = if (incluirId && id > 0) id.toLong() else null,
        asignatura = asignatura.trim(),
        unidad = unidad.trim(),
        objetivo = objetivo.trim(),
        tipo = tipo.trim(),
        titulo = titulo.trim(),
        contenido = contenido.trim(),
        pregunta = pregunta.trim().ifBlank { null },
        imagenUri = imagenUri?.ifBlank { null },
        estado = estado.name
    )
}
