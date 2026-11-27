package com.example.appmpazpro2.data.remote

import com.example.appmpazpro2.data.model.ContenidoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BackendApiService {

    @GET("api/contenidos")
    suspend fun listarContenidos(): List<ContenidoDto>

    @GET("api/contenidos/{id}")
    suspend fun buscarContenidoPorId(
        @Path("id") id: Long
    ): ContenidoDto

    @POST("api/contenidos")
    suspend fun crearContenido(
        @Body contenido: ContenidoDto
    ): ContenidoDto

    @PUT("api/contenidos/{id}")
    suspend fun actualizarContenido(
        @Path("id") id: Long,
        @Body contenido: ContenidoDto
    ): ContenidoDto

    @DELETE("api/contenidos/{id}")
    suspend fun eliminarContenido(
        @Path("id") id: Long
    ): Response<Unit>
}
