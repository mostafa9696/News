package com.mostafa.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("results") val news: List<NewsRemote>
)

data class NewsRemote(
    @SerializedName("published_date") val publishedDate: String
)