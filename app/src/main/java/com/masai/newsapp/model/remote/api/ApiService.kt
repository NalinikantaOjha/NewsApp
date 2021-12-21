package com.masai.newsapp.model.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("v2/everything")
    suspend fun getData(
        @Query("q") q:String,
    @Query("apiKey")apiKey:String): Response<com.masai.newsapp.model.remote.Response>
}