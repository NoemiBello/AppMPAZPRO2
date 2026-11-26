package com.example.appmpazpro2.repository

import com.example.appmpazpro2.data.model.LibroRecomendado
import com.example.appmpazpro2.data.model.OpenLibraryBookDoc
import com.example.appmpazpro2.data.remote.OpenLibraryApiService
import com.example.appmpazpro2.data.remote.OpenLibraryRetrofitInstance

interface BibliotecaRepository {
    suspend fun obtenerLibrosRecomendados(limit: Int = 10): List<LibroRecomendado>
    suspend fun buscarLibros(consulta: String, limit: Int = 10): List<LibroRecomendado>
}

open class BibliotecaRepositoryImpl(
    private val api: OpenLibraryApiService = OpenLibraryRetrofitInstance.api
) : BibliotecaRepository {

    override suspend fun obtenerLibrosRecomendados(limit: Int): List<LibroRecomendado> {
        val response = api.buscarPorAutor(author = "marcela paz", limit = limit)
        return mapearYLimpiarLibros(response.docs)
    }

    override suspend fun buscarLibros(consulta: String, limit: Int): List<LibroRecomendado> {
        val queryLimpia = consulta.trim()
        if (queryLimpia.isBlank()) {
            return obtenerLibrosRecomendados(limit)
        }
        val response = api.buscarLibros(query = queryLimpia, limit = limit)
        return mapearYLimpiarLibros(response.docs)
    }

    internal fun mapearYLimpiarLibros(docs: List<OpenLibraryBookDoc>): List<LibroRecomendado> {
        val vistos = mutableSetOf<String>()
        return docs.mapNotNull { doc ->
            val titulo = doc.title?.trim().orEmpty()
            if (titulo.isBlank()) return@mapNotNull null

            val claveNormalizada = normalizarTitulo(titulo)
            if (!vistos.add(claveNormalizada)) return@mapNotNull null

            val autor = doc.authorName
                ?.firstOrNull { it.isNotBlank() }
                ?.trim()
                ?: "Autor desconocido"

            val portadaUrl = doc.coverId?.let {
                "https://covers.openlibrary.org/b/id/$it-M.jpg"
            }

            LibroRecomendado(
                id = doc.key ?: claveNormalizada,
                titulo = titulo,
                autor = autor,
                anioPublicacion = doc.firstPublishYear,
                portadaUrl = portadaUrl
            )
        }
    }

    private fun normalizarTitulo(titulo: String): String {
        return titulo.lowercase()
            .replace(Regex("[^a-z0-9áéíóúñ ]"), "")
            .replace(Regex("\\s+"), " ")
            .trim()
    }
}
