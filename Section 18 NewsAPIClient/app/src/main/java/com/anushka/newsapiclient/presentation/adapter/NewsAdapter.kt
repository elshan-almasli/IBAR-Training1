package com.anushka.newsapiclient.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anushka.newsapiclient.data.model.Article
import com.anushka.newsapiclient.databinding.NewsListItemBinding
import com.bumptech.glide.Glide

class NewsAdapter : PagingDataAdapter<Article, NewsAdapter.NewsViewHolder>(DataDifferator) {


    object DataDifferator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    inner class NewsViewHolder(
        val binding: NewsListItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.source.name

            Glide.with(binding.ivArticleImage.context).load(article.urlToImage)
                .into(binding.ivArticleImage)

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }


}









