package com.miftah.newsnice.ui.Bookmark

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miftah.newsnice.R
import com.miftah.newsnice.core.di.Injection
import com.miftah.newsnice.ui.common.MainCard
import com.miftah.newsnice.ui.theme.Red
import com.miftah.newsnice.ui.theme.White
import com.miftah.newsnice.utils.ViewModelFactory
import com.miftah.newsnice.utils.Result

@Composable
fun BookmarkPage(
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
    navigateToHomepage : () -> Unit
) {
    val news = viewModel.getBookmarkedNews().asFlow().collectAsState(initial = null)

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
                IconButton(
                    modifier = Modifier.padding(end = 16.dp),
                    onClick = {
                        navigateToHomepage()
                    }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ep_arrow_left_bold),
                        contentDescription = null
                    )
                }
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
                Spacer(modifier = Modifier.width(24.dp))
            }
        }
    ) { innerPadding ->
        news.value?.let { data ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
            ) {
                items(data) { data ->
                    MainCard(
                        url = data.urlToImage,
                        title = data.title,
                        isBookmarked = true,
                        callback = {
                            viewModel.deleteNews(data)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

    }
}

@Preview
@Composable
private fun BookmarkPagePreview() {

}