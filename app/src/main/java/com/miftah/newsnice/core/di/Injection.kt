package com.miftah.newsnice.core.di

import android.content.Context
import com.miftah.newsnice.core.NewsRepository
import com.miftah.newsnice.core.local.room.NewsDatabase
import com.miftah.newsnice.core.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        val database = NewsDatabase.getInstance(context)
        val dao = database.newsDao()
        return NewsRepository.getInstance(apiService, dao)
    }
}