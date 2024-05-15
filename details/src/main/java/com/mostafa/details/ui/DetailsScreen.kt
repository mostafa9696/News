package com.mostafa.details.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mostafa.base.compose.AnimatedAsyncImage
import com.mostafa.base.compose.AppScaffold
import com.mostafa.base.compose.PublishedDateView
import com.mostafa.base.model.NewsPresentation
import com.mostafa.base.utils.Dimens

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    item: NewsPresentation,
) {

    AppScaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        DetailsContent(modifier.padding(it), item)
    }
}

@Composable
fun DetailsContent(modifier: Modifier, item: NewsPresentation) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.twoLevelPadding)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomStart
        ) {
            AnimatedAsyncImage(
                imageUrl = item.getPosterOrThumb(),
                modifier = Modifier
                    .height(LocalConfiguration.current.screenHeightDp.dp / 3)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind { drawRect(color = Color(0x54000000)) }
                    .padding(Dimens.twoLevelPadding)
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = item.byline,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(Dimens.oneLevelPadding))
                PublishedDateView(item.publishedDate)
            }
        }

        Text(
            modifier = Modifier.padding(horizontal = Dimens.twoLevelPadding),
            text = item.title,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            modifier = Modifier.padding(horizontal = Dimens.twoLevelPadding),
            text = item.abstract,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(Dimens.twoLevelPadding))

        val urlHandler = LocalUriHandler.current
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.twoLevelPadding),
            onClick = {
                urlHandler.openUri(item.url)
            },
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(30),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Read more")
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
