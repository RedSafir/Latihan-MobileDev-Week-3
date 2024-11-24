package com.miftah.newsnice.ui.Bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miftah.newsnice.core.NewsRepository
import com.miftah.newsnice.core.local.entity.NewsEntity
import com.miftah.newsnice.core.remote.data.ArticlesItem
import kotlinx.coroutines.launch

class BookmarkViewModel(val repository: NewsRepository) : ViewModel() {
    fun getBookmarkedNews() = repository.getBookmarkedNews()

    fun checkIfBookmarked(title : String) : Boolean {
        var result = false
        viewModelScope.launch {
            val isBookmarked = repository.isBookMarked(title)
            result = isBookmarked
        }
        return result
    }

    fun saveNews(articlesItem: ArticlesItem) {
        viewModelScope.launch {
            repository.saveBookMark(articlesItem)
        }
    }

    fun deleteNews(articlesItem: NewsEntity) {
        viewModelScope.launch {
            repository.deleteBookMark(articlesItem.title)
        }
    }
}