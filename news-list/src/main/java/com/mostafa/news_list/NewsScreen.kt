package com.mostafa.news_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.Dimens
import com.mostafa.news_list.model.NewsPresentation

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    onNewsClick: (news: NewsPresentation) -> Unit,
    viewModel: NewsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        NewsList(uiState.news, onNewsClick, modifier)
    }
}

@Composable
fun NewsList(
    news: List<NewsPresentation>,
    onNewsClick: (news: NewsPresentation) -> Unit,
    modifier: Modifier,
) {
    LazyColumn(
        modifier = modifier
            .padding(Dimens.threeLevelPadding)
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = Dimens.oneLevelPadding),
        state = rememberLazyListState()
    ) {
        items(news.size) { index ->
            NewsItem(news[index], onNewsClick)
            Spacer(modifier = Modifier.height(Dimens.twoLevelPadding))
            Divider(color = Color.Gray)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsItem(
    newsPresentation: NewsPresentation,
    onNewsClick: (news: NewsPresentation) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.threeLevelPadding)
            .clickable { onNewsClick(newsPresentation) },
        horizontalArrangement = Arrangement.spacedBy(Dimens.oneLevelPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedAsyncImage(
            imageUrl = newsPresentation.thumbnail,
            modifier = Modifier.size(60.dp)
        )

        Column {
            Text(
                text = newsPresentation.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Row(
                modifier = Modifier.padding(
                    top = Dimens.oneLevelPadding,
                    start = Dimens.oneLevelPadding
                ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                    text = newsPresentation.byline,
                    modifier = Modifier.weight(1f),
                )
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = Color.Gray
                )
                Text(
                    color = Color.Gray, text = newsPresentation.publishedDate,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Preview
@Composable
fun ArticleItemPreview() {
    NewsItem(
        newsPresentation = NewsPresentation(
            1,
            title = "Biden Pardons Thousands Convicted of Marijuana Possession Under Federal Law",
            abstract = "https://static01.nyt.com/images/2022/10/06/us/politics/06dc-Marijuana/merlin_204885015_0539936e-5bd1-4b45-93d7-5f5c0c80de4b-thumbStandard.jpg",
            publishedDate = "2022-10-06",
            byline = "By Michael D. Shear and Zolan Kanno-Youngs",
            thumbnail = "",
            poster = "",
            url = ""
        )
    ) {}
}
