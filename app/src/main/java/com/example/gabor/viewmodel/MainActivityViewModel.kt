package com.example.gabor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gabor.api.NetworkCallOutput
import com.example.gabor.data.NewsArticle
import com.example.gabor.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _data: MutableLiveData<NetworkCallOutput<List<NewsArticle>>> = MutableLiveData(NetworkCallOutput.Success(
        emptyList()))
    val data: LiveData<NetworkCallOutput<List<NewsArticle>>> get() = _data

    private var job: Job? = null
    private var term: String? = null

    init { term?.let { pullArticles(it) } }

    fun pullArticles(term: String? = null) {
        if (term == null || job != null) return
        this.term = term
        _data.value = NetworkCallOutput.Loading
        job = viewModelScope.launch {
            val result = try {
                repository.getNews(term)
            } catch (e: Exception) {
                NetworkCallOutput.Error(e.message ?: "Unknown error")
            } finally {
                job = null
            }
            _data.postValue(result)
        }
    }
}