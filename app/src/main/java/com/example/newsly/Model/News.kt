package com.example.newsly.Model

data class News(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)