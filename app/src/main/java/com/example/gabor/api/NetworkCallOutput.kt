package com.example.gabor.api

sealed class NetworkCallOutput<out T: Any> {
    data class Success<out T : Any>(val data: T) : NetworkCallOutput<T>()
    object Loading: NetworkCallOutput<Nothing>()
    data class Error(val error: String): NetworkCallOutput<Nothing>()
}