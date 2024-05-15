package com.mostafa.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mostafa.base.model.NewsPresentation

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    newsPresentation: NewsPresentation,
) {

    Column(modifier = modifier) {

    }
    Text(modifier = Modifier.padding(20.dp), text = newsPresentation.publishedDate)
}