package com.example.bookapp.data.local

import com.example.bookapp.domain.model.Book

fun Book.toEntity(): FavoriteBookEntity = FavoriteBookEntity(
    id, title, authors, description, thumbnail, previewLink
)

fun FavoriteBookEntity.toDomain(): Book = Book(
    id, title, authors, description, thumbnail, previewLink
)
