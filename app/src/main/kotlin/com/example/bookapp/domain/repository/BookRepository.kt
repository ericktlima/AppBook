package com.example.bookapp.domain.repository

import com.example.bookapp.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun searchBooks(query: String): Flow<List<Book>>
    suspend fun getBookById(id: String): Book?

}
