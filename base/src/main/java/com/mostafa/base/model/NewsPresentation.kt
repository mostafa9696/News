package com.mostafa.base.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsPresentation(
    val id: Long,
    val title: String,
    val abstract: String,
    val publishedDate: String,
    val url: String,
    val byline: String,
    val thumbnail: String,
    val poster: String,
) : Parcelable {

    fun getPosterOrThumb(): String {
        if (poster.isNotEmpty()) return poster
        return thumbnail
    }
}