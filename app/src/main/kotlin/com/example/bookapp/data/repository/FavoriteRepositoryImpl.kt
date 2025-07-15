package com.example.bookapp.data.repository

import com.example.bookapp.data.local.FavoriteBookDao
import com.example.bookapp.data.local.toDomain
import com.example.bookapp.data.local.toEntity
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteBookDao
) : FavoriteRepository {

    override suspend fun addToFavorites(book: Book) {
        dao.insert(book.toEntity())
    }

    override suspend fun removeFromFavorites(book: Book) {
        dao.delete(book.toEntity())
    }

    override suspend fun isFavorite(bookId: String): Boolean {
        return dao.getById(bookId) != null
    }

    override fun getAllFavorites(): Flow<List<Book>> {
        return dao.getAll().map { it.map { e -> e.toDomain() } }
    }
}
