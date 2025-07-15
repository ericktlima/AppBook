package com.example.bookapp.domain.model

data class Book(
    val id: String,
    val title: String,
    val authors: String,
    val description: String,
    val thumbnail: String?,
    val previewLink: String?
)