package com.mostafa.details.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mostafa.news_list.model.NewsPresentation

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    onPressBack: () -> Unit,
    newsPresentation: NewsPresentation,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    Text(modifier = Modifier.padding(20.dp), text = newsPresentation.publishedDate)
}