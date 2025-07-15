package com.example.bookapp.domain.usecase

import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.repository.BookRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchBooksUseCaseTest {

    private val repository = mockk<BookRepository>()
    private val useCase = SearchBooksUseCase(repository)

    @Test
    fun `invoke should return list of books from repository`() = runTest {
        val books = listOf(
            Book("1", "Title1", "Author1", "Desc1", null, null)
        )

        coEvery { repository.searchBooks("android") } returns flowOf(books)

        val result = useCase("android")

        result.collect {
            assertEquals(books, it)
        }
    }
}
