package com.masai.newsapp.model.remote


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)