package com.example.bookapp.presentation.screens

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.usecase.*
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BookListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val searchBooksUseCase = mockk<SearchBooksUseCase>()
    private val addToFavoritesUseCase = mockk<AddToFavoritesUseCase>(relaxed = true)
    private val removeFromFavoritesUseCase = mockk<RemoveFromFavoritesUseCase>(relaxed = true)
    private val isFavoriteUseCase = mockk<IsFavoriteUseCase>()

    private lateinit var viewModel: BookListViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        every { searchBooksUseCase.invoke(any()) } returns flowOf(emptyList())
        coEvery { isFavoriteUseCase.invoke(any()) } returns false

        viewModel = BookListViewModel(
            searchBooksUseCase,
            addToFavoritesUseCase,
            removeFromFavoritesUseCase,
            isFavoriteUseCase
        )
    }

    @Test
    fun `searchBooks updates books state`() = runTest {
        val books = listOf(Book("1", "title", "author", "desc", null, null))
        every { searchBooksUseCase.invoke("query") } returns flowOf(books)

        viewModel.searchBooks("query")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(books, viewModel.books.value)
    }
}
