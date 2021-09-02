package com.example.gabor.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gabor.data.NewsArticle
import com.example.gabor.databinding.ItemNewsBinding

class NewsViewHolder(
    private val binding: ItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: NewsArticle, listener: ((NewsArticle) -> Unit)? = null) {
        binding.apply {
            itemView.setOnClickListener {
                listener?.invoke(news)
            }
            Glide.with(itemView)
                .load(news.thumbnailUrl)
                .override(news.width ?: 0, news.height ?: 0)
                .into(imageView)

            textViewTitle.text = news.title ?: ""
        }
    }
}