package com.example.mvisample.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.mvisample.R
import com.example.mvisample.repository.NewsItem
import com.example.mvisample.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_view.view.*

/**
 *
 * -----------------------------------------------------------------
 * Copyright (C) 2021, by Sumpay, All rights reserved.
 * -----------------------------------------------------------------
 * desc: NewsRvAdapter
 * Author: wangjp
 * Email: wangjp1@fosun.com
 * Version: Vx.x.x
 * Create: 2022/2/17 3:19 下午
 *
 */
class NewsRvAdapter(private val listener: (View) -> Unit) :
    ListAdapter<NewsItem, NewsRvAdapter.MyViewHolder>(NewsItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(inflate(parent.context, R.layout.item_view, parent), listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MyViewHolder(override val containerView: View, listener: (View) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            itemView.setOnClickListener(listener)
        }

        fun bind(newsItem: NewsItem) = with(itemView) {
            itemView.tag = newsItem
            tv_title.text = newsItem.title
            tv_description.text = newsItem.description
            iv_thumbnail.load(newsItem.imageUrl) {
                crossfade(true)
                placeholder(R.mipmap.ic_launcher)
            }
        }
    }

    internal class NewsItemCallback : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.title == newItem.title
        }

    }
}