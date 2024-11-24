package com.miftah.newsnice.ui.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miftah.newsnice.core.NewsRepository
import com.miftah.newsnice.core.local.entity.NewsEntity
import com.miftah.newsnice.core.remote.data.ArticlesItem
import kotlinx.coroutines.launch

class HomeViewModel(val repository: NewsRepository) : ViewModel() {

    fun getNews() = repository.getNews()

    fun checkIfBookmarked(title: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val isBookmarked = repository.isBookMarked(title) // Query database
            callback(isBookmarked) // Kirim hasil ke callback
        }
    }

    fun saveNews(articlesItem: ArticlesItem) {
        viewModelScope.launch {
            repository.saveBookMark(articlesItem)
        }
    }

    fun deleteNews(articlesItem: ArticlesItem) {
        viewModelScope.launch {
            repository.deleteBookMark(articlesItem.title)
        }
    }
}