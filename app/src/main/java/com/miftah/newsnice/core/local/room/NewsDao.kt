package com.miftah.newsnice.core.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.miftah.newsnice.core.local.entity.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM news ORDER BY publishedAt DESC")
    fun getNews(): LiveData<List<NewsEntity>>

    @Query("DELETE FROM news WHERE title = :title")
    suspend fun delete(title : String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(news: NewsEntity)

    @Query("SELECT EXISTS(SELECT * FROM news WHERE title = :title)")
    suspend fun isNewsBookmarked(title: String): Boolean
}