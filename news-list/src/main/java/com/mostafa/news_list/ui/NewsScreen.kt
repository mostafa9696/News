package com.mostafa.news_list.ui

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mostafa.base.compose.AnimatedAsyncImage
import com.mostafa.base.compose.AppScaffold
import com.mostafa.base.compose.PublishedDateView
import com.mostafa.base.compose.ShowUserMessage
import com.mostafa.base.model.NewsPresentation
import com.mostafa.base.utils.Dimens
import com.mostafa.news_list.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NewsScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onNewsClick: (news: NewsPresentation) -> Unit,
    viewModel: NewsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        if (uiState.newsState !is NewsContract.NewsUiState.DisplayNews)
            viewModel.setEvent(NewsContract.Event.OnLoadNewsList)
    }

    when (val state = uiState.newsState) {
        is NewsContract.NewsUiState.Loading -> ShimmerEffect()
        is NewsContract.NewsUiState.DisplayError -> ShowUserMessage(
            message = state.errorMessage ?: ""
        )

        is NewsContract.NewsUiState.DisplayNews -> {
            AppScaffold(
                modifier = modifier.fillMaxSize(),
                backgroundColor = MaterialTheme.colorScheme.background
            ) {
                NewsContent(
                    modifier.padding(it),
                    animatedVisibilityScope,
                    state.news,
                    onNewsClick
                )
            }
        }

        is NewsContract.NewsUiState.Idle -> {}
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NewsContent(
    modifier: Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    news: List<NewsPresentation>,
    onNewsClick: (news: NewsPresentation) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        NewsBar()

        NewsList(
            news = news,
            onNewsClick = onNewsClick,
            animatedVisibilityScope = animatedVisibilityScope,
        )
    }

}

@Composable
fun NewsBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = Dimens.twoLevelPadding)
    ) {
        Image(
            modifier = Modifier.size(
                32.dp
            ),
            painter = painterResource(id = R.drawable.nytimes_logo),
            contentDescription = null
        )
        Text(
            text = "NYTimes News",
            style = MaterialTheme.typography.headlineSmall,
        )
    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NewsList(
    modifier: Modifier = Modifier,
    news: List<NewsPresentation>,
    onNewsClick: (news: NewsPresentation) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = Dimens.twoLevelPadding),
        state = rememberLazyListState()
    ) {
        items(news.size) { index ->
            NewsItem(news[index], onNewsClick, animatedVisibilityScope)
            Spacer(modifier = Modifier.height(Dimens.twoLevelPadding))
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NewsItem(
    newsPresentation: NewsPresentation,
    onNewsClick: (news: NewsPresentation) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                horizontal = Dimens.threeLevelPadding,
                vertical = Dimens.twoLevelPadding
            )
            .clickable { onNewsClick(newsPresentation) },
        horizontalArrangement = Arrangement.spacedBy(Dimens.oneLevelPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedAsyncImage(
            imageUrl = newsPresentation.thumbnail,
            modifier = Modifier
                .sharedElement(
                    rememberSharedContentState(key = "image-${newsPresentation.id}"),
                    animatedVisibilityScope,
                ).skipToLookaheadSize()
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Column(modifier = Modifier.padding(start = Dimens.oneLevelPadding)) {
            Text(
                modifier = Modifier.sharedElement(
                    state = rememberSharedContentState(
                        key = "title-${newsPresentation.id}}"
                    ),
                    animatedVisibilityScope = animatedVisibilityScope,
                ),
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
                PublishedDateView(newsPresentation.publishedDate, Color.Gray)
            }
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.oneLevelPadding),
        modifier = Modifier.padding(top = Dimens.fiveLevelPadding)
    ) {
        repeat(10) {
            ItemShimmerEffect(
                modifier = Modifier.padding(Dimens.twoLevelPadding)
            )
        }
    }
}

@Preview
@Composable
fun ArticleItemPreview() {
    ShimmerEffect()
}
