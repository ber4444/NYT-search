package com.example.gabor.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {

    companion object {
        const val BASE_URL = "https://api.nytimes.com/svc/search/v2/"
        const val API_KEY = "38XK5KGWpGRRfhqZ02KOr5b2VSJwq1Aq" // TODO use BuildConfig (in a real project, you wouldn't want to check this in to git)
    }

    @GET("articlesearch.json")
    suspend fun getBreakingNews(@Query("q") searchTerm: String,
                                @Query("api-key") apiKey: String = API_KEY): Response<NewsResponse>
}