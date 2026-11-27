package com.example.appmpazpro2.repository

import com.example.appmpazpro2.data.model.ContenidoDto
import com.example.appmpazpro2.data.remote.BackendApiService
import com.example.appmpazpro2.model.ContenidoUiState
import com.example.appmpazpro2.model.EstadoContenido
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class ContenidoRepositoryTest : StringSpec({

    val dtoEjemplo = ContenidoDto(
        id = 1L,
        asignatura = "Lenguaje",
        unidad = "Unidad 1",
        objetivo = "Comprender textos narrativos",
        tipo = "Lectura",
        titulo = "Mi primera lectura",
        contenido = "Habia una vez un gato en el campo.",
        pregunta = "Donde vivia el gato?",
        imagenUri = null,
        estado = "APROBADO"
    )

    "obtenerContenidos mapea correctamente la lista de ContenidoDto a ContenidoUiState" {
        val mockApi = mockk<BackendApiService>()
        coEvery { mockApi.listarContenidos() } returns listOf(
            dtoEjemplo,
            dtoEjemplo.copy(id = 2L, estado = "ESTADO_DESCONOCIDO", imagenUri = "   ")
        )

        val repo = ContenidoRepositoryImpl(mockApi)
        runTest {
            val lista = repo.obtenerContenidos()
            lista shouldHaveSize 2
            lista[0].id shouldBe 1
            lista[0].estado shouldBe EstadoContenido.APROBADO
            lista[0].pregunta shouldBe "Donde vivia el gato?"
            lista[1].estado shouldBe EstadoContenido.PENDIENTE
            lista[1].imagenUri.shouldBeNull()
        }
    }

    "obtenerPorId retorna el contenido solicitado" {
        val mockApi = mockk<BackendApiService>()
        coEvery { mockApi.buscarContenidoPorId(1L) } returns dtoEjemplo

        val repo = ContenidoRepositoryImpl(mockApi)
        runTest {
            val item = repo.obtenerPorId(1)
            item.titulo shouldBe "Mi primera lectura"
            item.asignatura shouldBe "Lenguaje"
        }
    }

    "crearContenido envia POST sin ID y retorna contenido creado" {
        val mockApi = mockk<BackendApiService>()
        coEvery { mockApi.crearContenido(any()) } returns dtoEjemplo.copy(id = 15L)

        val repo = ContenidoRepositoryImpl(mockApi)
        val input = ContenidoUiState(
            id = 1,
            asignatura = "Lenguaje",
            unidad = "Unidad 1",
            objetivo = "OA 5",
            tipo = "Lectura",
            titulo = "Mi primera lectura",
            contenido = "Texto",
            pregunta = "Pregunta?",
            estado = EstadoContenido.PENDIENTE
        )

        runTest {
            val creado = repo.crearContenido(input)
            creado.id shouldBe 15
        }
    }

    "actualizarContenido envia PUT con ID y retorna contenido actualizado" {
        val mockApi = mockk<BackendApiService>()
        coEvery { mockApi.actualizarContenido(1L, any()) } returns dtoEjemplo.copy(titulo = "Editado", estado = "OCULTO")

        val repo = ContenidoRepositoryImpl(mockApi)
        runTest {
            val actualizado = repo.actualizarContenido(
                1,
                ContenidoUiState(
                    id = 1,
                    asignatura = "Lenguaje",
                    unidad = "Unidad 1",
                    objetivo = "OA 5",
                    tipo = "Lectura",
                    titulo = "Editado",
                    contenido = "Texto",
                    pregunta = "",
                    estado = EstadoContenido.OCULTO
                )
            )
            actualizado.titulo shouldBe "Editado"
            actualizado.estado shouldBe EstadoContenido.OCULTO
        }
    }

    "eliminarContenido retorna true en 204 y false en 404" {
        val mockApi = mockk<BackendApiService>()
        coEvery { mockApi.eliminarContenido(1L) } returns Response.success(Unit)
        coEvery { mockApi.eliminarContenido(99L) } returns Response.error(404, "Not Found".toResponseBody())

        val repo = ContenidoRepositoryImpl(mockApi)
        runTest {
            repo.eliminarContenido(1) shouldBe true
            repo.eliminarContenido(99) shouldBe false
        }
    }
})
