package com.mostafa.base.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mostafa.base.utils.Dimens

@Composable
fun PublishedDateView(publishedDate: String, color: Color){
    Row(
        modifier = Modifier.padding(
            top = Dimens.oneLevelPadding,
            start = Dimens.oneLevelPadding
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = null,
            tint = color
        )
        Text(
            color = color, text = publishedDate,
            style = MaterialTheme.typography.titleSmall,
        )
    }
}