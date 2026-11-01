package com.example.appmpazpro2.model

enum class EstadoRespuesta {
    PENDIENTE_REVISION,
    CORRECTA,
    INCORRECTA
}

data class RespuestaUiState(
    val id: Int = 0,
    val contenidoId: Int = 0,
    val respuesta: String = "",
    val estado: EstadoRespuesta = EstadoRespuesta.PENDIENTE_REVISION
)