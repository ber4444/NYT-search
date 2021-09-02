package com.example.gabor.repository

import com.example.gabor.api.NetworkCallOutput
import com.example.gabor.api.NewsApi
import com.example.gabor.data.NewsArticle

class NewsRepositoryImpl(private val newsApi: NewsApi) : NewsRepository {

    override suspend fun getNews(searchTerm: String): NetworkCallOutput<List<NewsArticle>> {
        val response = newsApi.getBreakingNews(searchTerm)
        return if (response.isSuccessful && response.body() != null) {
            response.body()!!.response.docs?.let { res ->
                NetworkCallOutput.Success(convert(res))
            } ?: NetworkCallOutput.Error("Empty response")
        } else {
            NetworkCallOutput.Error(response.message())
        }
    }
}