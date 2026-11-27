package com.example.appmpazpro2.repository

import com.example.appmpazpro2.data.model.OpenLibraryBookDoc
import com.example.appmpazpro2.data.model.OpenLibrarySearchResponse
import com.example.appmpazpro2.data.remote.OpenLibraryApiService
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest

class BibliotecaRepositoryTest : StringSpec({

    "obtenerLibrosRecomendados debe mapear libros de Marcela Paz, eliminar duplicados y manejar nulos" {
        val mockApi = mockk<OpenLibraryApiService>()
        val respuesta = OpenLibrarySearchResponse(
            numFound = 4,
            docs = listOf(
                OpenLibraryBookDoc(
                    key = "/works/OL1W",
                    title = "Papelucho",
                    authorName = listOf("Marcela Paz"),
                    firstPublishYear = 1984,
                    coverId = 12679281
                ),
                OpenLibraryBookDoc(
                    key = "/works/OL2W",
                    title = "PAPELUCHO",
                    authorName = listOf("Marcela Paz"),
                    firstPublishYear = 2013,
                    coverId = null
                ),
                OpenLibraryBookDoc(
                    key = "/works/OL3W",
                    title = "Los pecosos",
                    authorName = null,
                    firstPublishYear = 1980,
                    coverId = null
                ),
                OpenLibraryBookDoc(
                    key = "/works/OL4W",
                    title = "   ",
                    authorName = listOf("Marcela Paz"),
                    firstPublishYear = 1990,
                    coverId = null
                )
            )
        )

        coEvery { mockApi.buscarPorAutor(author = "marcela paz", limit = 10) } returns respuesta

        val repo = BibliotecaRepositoryImpl(api = mockApi)

        runTest {
            val libros = repo.obtenerLibrosRecomendados()
            libros shouldHaveSize 2

            libros[0].id shouldBe "/works/OL1W"
            libros[0].titulo shouldBe "Papelucho"
            libros[0].autor shouldBe "Marcela Paz"
            libros[0].anioPublicacion shouldBe 1984
            libros[0].portadaUrl shouldBe "https://covers.openlibrary.org/b/id/12679281-M.jpg"

            libros[1].titulo shouldBe "Los pecosos"
            libros[1].autor shouldBe "Autor desconocido"
            libros[1].portadaUrl.shouldBeNull()
        }
    }

    "buscarLibros con consulta vacia delega a obtenerLibrosRecomendados" {
        val mockApi = mockk<OpenLibraryApiService>()
        coEvery { mockApi.buscarPorAutor(author = "marcela paz", limit = 10) } returns OpenLibrarySearchResponse(
            numFound = 1,
            docs = listOf(
                OpenLibraryBookDoc(key = "/works/1", title = "Perico trepa por Chile", authorName = listOf("Marcela Paz"), firstPublishYear = 2005, coverId = 123)
            )
        )

        val repo = BibliotecaRepositoryImpl(api = mockApi)

        runTest {
            val libros = repo.buscarLibros("   ")
            libros shouldHaveSize 1
            libros[0].titulo shouldBe "Perico trepa por Chile"
            coVerify(exactly = 1) { mockApi.buscarPorAutor(author = "marcela paz", limit = 10) }
        }
    }

    "buscarLibros con texto valido consulta buscarLibros en OpenLibraryApiService" {
        val mockApi = mockk<OpenLibraryApiService>()
        coEvery { mockApi.buscarLibros(query = "cuentos infantiles", limit = 5) } returns OpenLibrarySearchResponse(
            numFound = 1,
            docs = listOf(
                OpenLibraryBookDoc(key = null, title = "Cuentos del sur", authorName = listOf("Varios"), firstPublishYear = 2010, coverId = null)
            )
        )

        val repo = BibliotecaRepositoryImpl(api = mockApi)

        runTest {
            val libros = repo.buscarLibros("  cuentos infantiles  ", limit = 5)
            libros shouldHaveSize 1
            libros[0].titulo shouldBe "Cuentos del sur"
            libros[0].id shouldBe "cuentos del sur"
        }
    }
})
