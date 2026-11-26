package com.example.appmpazpro2.data.model

import com.google.gson.annotations.SerializedName

data class OpenLibrarySearchResponse(
    @SerializedName("numFound")
    val numFound: Int = 0,
    @SerializedName("docs")
    val docs: List<OpenLibraryBookDoc> = emptyList()
)

data class OpenLibraryBookDoc(
    @SerializedName("key")
    val key: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("author_name")
    val authorName: List<String>? = null,
    @SerializedName("first_publish_year")
    val firstPublishYear: Int? = null,
    @SerializedName("cover_i")
    val coverId: Int? = null
)

data class LibroRecomendado(
    val id: String,
    val titulo: String,
    val autor: String,
    val anioPublicacion: Int?,
    val portadaUrl: String?
)
