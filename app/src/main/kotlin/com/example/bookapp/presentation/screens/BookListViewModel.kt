package com.example.bookapp.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.usecase.AddToFavoritesUseCase
import com.example.bookapp.domain.usecase.IsFavoriteUseCase
import com.example.bookapp.domain.usecase.RemoveFromFavoritesUseCase
import com.example.bookapp.domain.usecase.SearchBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val searchBooksUseCase: SearchBooksUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase
) : ViewModel() {

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books.asStateFlow()

    private val _query = MutableStateFlow("android")
    val query: StateFlow<String> = _query

    init {
        searchBooks("android")
    }

    fun searchBooks(query: String) {
        _query.value = query
        viewModelScope.launch {
            searchBooksUseCase(query)
                .catch { e -> e.printStackTrace() }
                .collect { _books.value = it }
        }
    }


    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    fun checkFavorite(bookId: String) {
        viewModelScope.launch {
            _isFavorite.value = isFavoriteUseCase(bookId)
        }
    }

    fun toggleFavorite(book: Book) {
        viewModelScope.launch {
            if (_isFavorite.value) {
                removeFromFavoritesUseCase(book)
            } else {
                addToFavoritesUseCase(book)
            }
            _isFavorite.value = !_isFavorite.value
        }
    }
}
