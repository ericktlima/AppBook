package com.example.bookapp.data.remote

import com.example.bookapp.domain.model.Book

fun BookDto.toDomain(): Book {
    return Book(
        id = this.id,
        title = this.volumeInfo.title ?: "",
        authors = this.volumeInfo.authors?.joinToString(", ") ?: "",
        description = this.volumeInfo.description ?: "",
        thumbnail = this.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://"),
        previewLink = this.volumeInfo.previewLink
    )
}
