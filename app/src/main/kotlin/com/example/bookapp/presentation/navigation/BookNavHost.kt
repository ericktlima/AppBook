package com.example.bookapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookapp.presentation.screens.BookListScreen
import com.example.bookapp.presentation.screens.BookDetailScreen
import com.example.bookapp.presentation.screens.FavoritesScreen

@Composable
fun BookNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "book_list",
        modifier = modifier
    ) {
        composable("book_list") {
            BookListScreen(navController)
        }
        composable("book_detail/{bookId}") {
            BookDetailScreen(navController)
        }
        composable("favorites") {
            FavoritesScreen(navController)
        }
    }
}
