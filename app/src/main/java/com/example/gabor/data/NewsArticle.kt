package com.example.gabor.data

data class NewsArticle(
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?,
    val width: Int?,
    val height: Int?,
    val publishedAt: String? = null,
    val isBookmarked: Boolean = false,
    val updatedAt: Long = System.currentTimeMillis()
)