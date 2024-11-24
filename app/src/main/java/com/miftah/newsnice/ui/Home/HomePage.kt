package com.miftah.newsnice.ui.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miftah.newsnice.R
import com.miftah.newsnice.core.di.Injection
import com.miftah.newsnice.ui.Bookmark.BookmarkViewModel
import com.miftah.newsnice.ui.common.MainCard
import com.miftah.newsnice.ui.theme.NewsNiceTheme
import com.miftah.newsnice.ui.theme.Poppins
import com.miftah.newsnice.ui.theme.Red
import com.miftah.newsnice.ui.theme.White
import com.miftah.newsnice.utils.Result
import com.miftah.newsnice.utils.ViewModelFactory

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current),
    ),
    navigateToBookmark : () -> Unit
) {
    val news = viewModel.getNews().asFlow().collectAsState(initial = Result.Loading)

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Red),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(24.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    modifier = Modifier
                        .weight(3f)
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = White,
                        fontWeight = FontWeight.Bold
                    ),
                    text = "ITENICE NEWS"
                )
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(
                    modifier = Modifier.padding(end = 16.dp),
                    onClick = {
                        navigateToBookmark()
                    }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.material_symbols_bookmark),
                        contentDescription = null
                    )
                }
            }
        }
    ) { innerPadding ->
        news.value.let { result ->
            when(result) {
                is Result.Loading -> {

                }

                is Result.Error -> {
                    Text(text = "Something Wrong")
                }
                is Result.Success -> {
                    LazyColumn(
                        modifier = Modifier.padding(innerPadding),
                        contentPadding = PaddingValues(16.dp),
                    ) {
                        items(result.data) { data ->
                            var isBookmarked by remember { mutableStateOf(false) }
                            LaunchedEffect(data.title) {
                                viewModel.checkIfBookmarked(data.title) { bookmarked ->
                                    isBookmarked = bookmarked
                                }
                            }
                            MainCard(
                                url = data.urlToImage,
                                title = data.title,
                                isBookmarked = isBookmarked,
                                callback = {
                                    isBookmarked = !isBookmarked
                                    if (isBookmarked) {
                                        viewModel.saveNews(data)
                                    } else {
                                        viewModel.deleteNews(data)
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }

    }
}

@Preview
@Composable
private fun HomePagePreview() {
    NewsNiceTheme {
//        HomePage()
    }
}