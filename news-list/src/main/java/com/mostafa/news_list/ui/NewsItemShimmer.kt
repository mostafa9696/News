package com.mostafa.news_list.ui

import android.annotation.SuppressLint
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mostafa.base.utils.Dimens
import com.mostafa.news_list.R

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")

    val alpha = transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ).value

    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}

@Composable
fun ItemShimmerEffect(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Color.White),
        horizontalArrangement = Arrangement.spacedBy(Dimens.oneLevelPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .shimmerEffect()
        )
        Column(modifier.padding(12.dp)) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(18.dp)
                    .shimmerEffect()
            )

            Box(
                modifier = modifier
                    .width(40.dp)
                    .height(18.dp)
                    .shimmerEffect()
            )

        }
    }
}