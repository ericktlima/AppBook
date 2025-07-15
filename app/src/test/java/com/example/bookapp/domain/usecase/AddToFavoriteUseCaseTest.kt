package com.example.bookapp.domain.usecase

import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.repository.FavoriteRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddToFavoriteUseCaseTest {


    private lateinit var repository: FavoriteRepository
    private lateinit var useCase: AddToFavoritesUseCase

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        useCase = AddToFavoritesUseCase(repository)
    }

    @Test
    fun `should call repository to add book to favorites`() = runTest {
        val book = Book(
            id = "abc123",
            title = "Clarice: A Paixão Segundo G.H.",
            authors = "Clarice Lispector",
            description = "Uma obra da literatura brasileira.",
            thumbnail = "https://example.com/clarice.jpg",
            previewLink = "https://example.com/preview"
        )

        useCase(book)

        coVerify(exactly = 1) {
            repository.addToFavorites(book)
        }
    }
}
