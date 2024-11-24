package com.miftah.newsnice.core.remote.retrofit

import androidx.lifecycle.LiveData
import com.miftah.newsnice.core.remote.data.ResponseNews
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything?q=Apple")
    suspend fun getNews(@Query("apiKey") apiKey: String): ResponseNews
}