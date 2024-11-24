package com.miftah.newsnice.core

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.miftah.newsnice.core.local.entity.NewsEntity
import com.miftah.newsnice.core.local.room.NewsDao
import com.miftah.newsnice.core.remote.data.ArticlesItem
import com.miftah.newsnice.core.remote.retrofit.ApiService
import com.miftah.newsnice.utils.APIKEY
import com.miftah.newsnice.utils.DataConverter
import com.miftah.newsnice.utils.Result
import retrofit2.HttpException

class NewsRepository private constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) {
    fun getNews(): LiveData<Result<List<ArticlesItem>>> = liveData {
        emit(Result.Loading)
        try {
            val client = apiService.getNews(APIKEY)
            emit(
                Result.Success(client.articles)
            )
        } catch (e: HttpException) {
            emit(Result.Error(e.message()))
        }
    }

    suspend fun isBookMarked(title: String) = newsDao.isNewsBookmarked(title)

    fun getBookmarkedNews(): LiveData<List<NewsEntity>> {
        return newsDao.getNews()
    }

    suspend fun saveBookMark(articlesItem: ArticlesItem) {
        newsDao.insertNews(
            NewsEntity(
                articlesItem.title,
                articlesItem.publishedAt,
                articlesItem.urlToImage,
                articlesItem.url
            )
        )
    }

    suspend fun deleteBookMark(title: String) = newsDao.delete(title)

    companion object {
        @Volatile
        private var instance: NewsRepository? = null
        fun getInstance(
            apiService: ApiService,
            newsDao: NewsDao
        ): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(apiService, newsDao)
            }.also { instance = it }
    }
}