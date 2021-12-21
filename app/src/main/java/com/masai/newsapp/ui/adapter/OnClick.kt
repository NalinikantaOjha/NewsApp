package com.masai.newsapp.ui.adapter

import com.masai.newsapp.model.local.NewsEntity
import com.masai.newsapp.model.remote.Article


interface OnClick {
    fun setOnClick(result: NewsEntity)
}