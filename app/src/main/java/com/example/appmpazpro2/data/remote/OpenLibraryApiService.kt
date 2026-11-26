package com.example.appmpazpro2.data.remote

import com.example.appmpazpro2.data.model.OpenLibrarySearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface OpenLibraryApiService {

    @Headers("User-Agent: MPAZPRO2-DuocUC/1.0")
    @GET("search.json")
    suspend fun buscarPorAutor(
        @Query("author") author: String = "marcela paz",
        @Query("fields") fields: String = "key,title,author_name,first_publish_year,cover_i",
        @Query("limit") limit: Int = 10
    ): OpenLibrarySearchResponse

    @Headers("User-Agent: MPAZPRO2-DuocUC/1.0")
    @GET("search.json")
    suspend fun buscarLibros(
        @Query("q") query: String,
        @Query("fields") fields: String = "key,title,author_name,first_publish_year,cover_i",
        @Query("limit") limit: Int = 10
    ): OpenLibrarySearchResponse
}
