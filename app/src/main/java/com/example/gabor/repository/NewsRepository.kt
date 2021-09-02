package com.example.gabor.repository

import com.example.gabor.api.NetworkCallOutput
import com.example.gabor.api.NewsArticleDto
import com.example.gabor.data.NewsArticle

interface NewsRepository {

    suspend fun getNews(searchTerm: String): NetworkCallOutput<List<NewsArticle>>

    fun convert(list: List<NewsArticleDto>): List<NewsArticle> {
        return list
            .filter  { ! it.abstract.isNullOrBlank() }
            .map {
            var thumb = it.multimedia?.firstOrNull()
            thumb =
                if (thumb?.url != null) thumb.copy(url = "https://www.nytimes.com/${thumb.url}") else null
            NewsArticle(
                it.abstract, it.webUrl, thumb?.url,
                thumb?.width, thumb?.height
            )
        }
    }
}