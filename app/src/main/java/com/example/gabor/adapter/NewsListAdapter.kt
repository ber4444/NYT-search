package com.example.gabor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.gabor.data.NewsArticle
import com.example.gabor.databinding.ItemNewsBinding

class NewsListAdapter : ListAdapter<NewsArticle, NewsViewHolder>(NewsComparator()) {

    var onItemClick: ((NewsArticle) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item, onItemClick)
        }
    }
}