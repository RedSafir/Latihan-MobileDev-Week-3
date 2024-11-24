package com.miftah.newsnice.utils

import com.miftah.newsnice.core.local.entity.NewsEntity
import com.miftah.newsnice.core.local.room.NewsDao
import com.miftah.newsnice.core.remote.data.ArticlesItem

object DataConverter {
//    fun fromArticlesItemToEntity(data : List<ArticlesItem>?, newsDao: NewsDao) :  List<NewsEntity>{
//        val newsList = ArrayList<NewsEntity>()
//        data?.forEach { article ->
//            val isBookmarked = newsDao.isNewsBookmarked(article.title)
//            val news = NewsEntity(
//                article.title,
//                article.publishedAt,
//                article.urlToImage,
//                article.url,
//                isBookmarked
//            )
//            newsList.add(news)
//        } ?: emptyList<NewsEntity>()
//        return newsList
//    }
}