package com.mostafa.news_list.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsPresentation(
    val publishedDate: String
): Parcelable