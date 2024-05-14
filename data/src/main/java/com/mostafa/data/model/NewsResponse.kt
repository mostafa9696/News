package com.mostafa.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("results") val news: List<NewsRemote>,
)

data class NewsRemote(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("abstract") val abstract: String,
    @SerializedName("published_date") val publishedDate: String,
    @SerializedName("url") val url: String,
    @SerializedName("byline") val byline: String,
    @SerializedName("media") val mediaList: List<MediaMetaData>,
) {
    fun getImagesUrl(): List<String> {
        val imagesUrl = mutableListOf<String>()
        mediaList.forEach {
            it.images.forEach { image ->
                imagesUrl.add(image.url ?: "")
            }
        }
        return imagesUrl
    }
}


data class MediaMetaData(
    @SerializedName("media-metadata") val images: List<Image>,
)

data class Image(
    val url: String? = "",
)
