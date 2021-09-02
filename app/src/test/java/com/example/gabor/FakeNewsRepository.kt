package com.example.gabor

import com.example.gabor.api.NetworkCallOutput
import com.example.gabor.api.NewsResponse
import com.example.gabor.data.NewsArticle
import com.example.gabor.repository.NewsRepository
import com.google.gson.Gson

class FakeNewsRepository : NewsRepository {
    override suspend fun getNews(searchTerm: String): NetworkCallOutput<List<NewsArticle>> {
        val article = javaClass.classLoader.getResourceAsStream("response.json")?.readBytes()
            ?.toString(Charsets.UTF_8)
        val list = Gson().fromJson(
            article, NewsResponse::class.java
        ).response.docs
        return NetworkCallOutput.Success(
            convert(list!!)
        )
    }
}