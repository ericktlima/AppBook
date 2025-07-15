package com.example.bookapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

data class GoogleBooksResponse(
    val items: List<BookDto>?
)

data class BookDto(
    val id: String,
    val volumeInfo: VolumeInfoDto
)

data class VolumeInfoDto(
    val title: String?,
    val authors: List<String>?,
    val description: String?,
    val imageLinks: ImageLinksDto?,
    val previewLink: String?
)

data class ImageLinksDto(
    val thumbnail: String?
)

interface GoogleBooksApiService {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 20
    ): GoogleBooksResponse

    @GET("volumes/{id}")
    suspend fun getBookById(@Path("id") id: String): BookDto
}
