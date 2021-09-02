package com.example.gabor.api

data class NewsResponse(val response: Res)

data class Res(val docs: List<NewsArticleDto>? = null)