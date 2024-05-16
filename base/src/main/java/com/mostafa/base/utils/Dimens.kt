package com.mostafa.base.utils

import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.dp

object Dimens {

    val oneLevelPadding = 4.dp
    val twoLevelPadding = 8.dp
    val threeLevelPadding = 16.dp
    val fourLevelPadding = 24.dp
    val fiveLevelPadding = 32.dp
    val sixLevelPadding = 48.dp

    val boundsTransform = { _: Rect, _: Rect -> tween<Rect>(550) }

}