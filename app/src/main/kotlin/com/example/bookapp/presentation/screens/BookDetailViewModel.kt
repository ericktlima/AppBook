package com.example.bookapp.presentation.screens

import GetBookByIdUseCase
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.usecase.AddToFavoritesUseCase
import com.example.bookapp.domain.usecase.IsFavoriteUseCase
import com.example.bookapp.domain.usecase.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookByIdUseCase: GetBookByIdUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _book = MutableStateFlow<Book?>(null)
    val book: StateFlow<Book?> = _book.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    init {
        val bookId: String? = savedStateHandle["bookId"]
        if (bookId != null) {
            viewModelScope.launch {
                val foundBook = getBookByIdUseCase(bookId)
                _book.value = foundBook

                foundBook?.let {
                    _isFavorite.value = isFavoriteUseCase(it.id)
                }
            }
        }
    }

    fun toggleFavorite(book: Book) {
        viewModelScope.launch {
            if (_isFavorite.value) {
                removeFromFavoritesUseCase(book)
                _isFavorite.value = false
            } else {
                addToFavoritesUseCase(book)
                _isFavorite.value = true
            }
        }
    }
}
