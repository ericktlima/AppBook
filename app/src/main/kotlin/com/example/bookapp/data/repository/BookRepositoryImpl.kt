package com.example.bookapp.data.repository

import com.example.bookapp.data.remote.GoogleBooksApiService
import com.example.bookapp.data.remote.toDomain
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: GoogleBooksApiService
) : BookRepository {
    override fun searchBooks(query: String): Flow<List<Book>> = flow {
        val response = api.searchBooks(query)
        val books = response.items?.map { it.toDomain() } ?: emptyList()
        emit(books)
    }

    override suspend fun getBookById(id: String): Book? {
        return try {
            val response = api.getBookById(id)
            if (response != null && response.volumeInfo != null) {
                response.toDomain()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
