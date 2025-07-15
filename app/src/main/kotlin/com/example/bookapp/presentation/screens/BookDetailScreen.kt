package com.example.bookapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.bookapp.domain.model.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    navController: NavController,
    viewModel: BookDetailViewModel = hiltViewModel()
) {
    val book by viewModel.book.collectAsState()

    val isFavorite by viewModel.isFavorite.collectAsState(initial = false) // Presumo que você tenha esse state na VM

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(book?.title ?: "Detalhes do Livro") },
                navigationIcon = {
                    IconButton(onClick = {
                        val popped = navController.popBackStack()
                        if (!popped) {
                            navController.navigate("book_list") {
                                launchSingleTop = true
                            }
                        }
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        book?.let {
            BookDetailContent(book = it, isFavorite = isFavorite) {
                viewModel.toggleFavorite(it)
            }
        } ?: Text("Livro não encontrado", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun BookDetailContent(book: Book, isFavorite: Boolean, onFavoriteClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val secureThumbnail = book.thumbnail?.replace("http://", "https://")

        secureThumbnail?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(book.title, style = MaterialTheme.typography.headlineSmall)
        Text("Autor(es): ${book.authors}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(book.description, style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onFavoriteClick) {
            Text(if (isFavorite) "Remover dos favoritos" else "Adicionar aos favoritos")
        }
    }
}
