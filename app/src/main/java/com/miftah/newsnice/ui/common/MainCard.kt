package com.miftah.newsnice.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.miftah.newsnice.R
import com.miftah.newsnice.ui.theme.NewsNiceTheme

@Composable
fun MainCard(modifier: Modifier = Modifier, url: String?, isBookmarked: Boolean, title: String, callback : (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .shadow(
                elevation = 1.dp
            )
    ) {
        Box(
            modifier = Modifier
                .height(120.dp)
                .weight(2f)
        ) {
            url?.let {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = url,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            IconButton(onClick = {
                callback(isBookmarked)
            }) {
                Icon(
                    imageVector = if (isBookmarked)
                        ImageVector.vectorResource(id = R.drawable.material_symbols_bookmark)
                    else
                        ImageVector.vectorResource(id = R.drawable.mdi_bookmark_outline),
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(3f)
                .padding(horizontal = 14.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                softWrap = true,
                style = MaterialTheme.typography.titleMedium,
                text = title
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
                text = "Blablabla"
            )
        }
    }
}

@Preview
@Composable
private fun MainCardPreview() {
    NewsNiceTheme {
        Column {
//            MainCard()
//            Spacer(modifier = Modifier.height(10.dp))
//            MainCard()
//            Spacer(modifier = Modifier.height(10.dp))
//            MainCard()
//            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}