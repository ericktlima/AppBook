package com.example.bookapp.domain.repository

import com.example.bookapp.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun addToFavorites(book: Book)
    suspend fun removeFromFavorites(book: Book)
    suspend fun isFavorite(bookId: String): Boolean
    fun getAllFavorites(): Flow<List<Book>>
}
