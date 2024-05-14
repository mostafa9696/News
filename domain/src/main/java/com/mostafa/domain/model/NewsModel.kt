package com.mostafa.domain.model


data class NewsModel(
    val id: Long,
    val title: String,
    val abstract: String,
    val publishedDate: String,
    val url: String,
    val byline: String,
    val images: List<ImageModel>,
)

data class ImageModel(
    val url: String
)