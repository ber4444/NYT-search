package com.example.gabor.api

import com.google.gson.annotations.SerializedName

data class NewsArticleDto(
    val abstract: String? = null,
    @SerializedName("web_url")
    val webUrl: String? = null,
    val multimedia: List<Multimedium>? = null
)

data class Multimedium(
    val url: String? = null,
    val width: Int? = null,
    val height: Int? = null
)