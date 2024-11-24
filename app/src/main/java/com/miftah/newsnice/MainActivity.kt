package com.miftah.newsnice

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miftah.newsnice.ui.Bookmark.BookmarkPage
import com.miftah.newsnice.ui.Bookmark.BookmarkViewModel
import com.miftah.newsnice.ui.Home.HomePage
import com.miftah.newsnice.ui.Home.HomeViewModel
import com.miftah.newsnice.ui.theme.NewsNiceTheme
import com.miftah.newsnice.utils.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsNiceTheme {
                NewsNiceApplication()
            }
        }
    }
}

@Composable
fun NewsNiceApplication(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "HomePage") {
        composable(route = "HomePage") {
            HomePage(
                navigateToBookmark = {
                    navController.navigate("BookmarkPage")
                }
            )
        }
        composable(route = "BookmarkPage") {
            BookmarkPage(
                navigateToHomepage = {
                    navController.popBackStack()
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsNiceTheme {

    }
}