package com.example.bookapp.domain.usecase

import com.example.bookapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(bookId: String): Boolean {
        return repository.isFavorite(bookId)
    }
}
