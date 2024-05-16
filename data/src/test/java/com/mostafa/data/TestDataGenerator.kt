package com.mostafa.data

import com.mostafa.data.model.NewsRemote
import com.mostafa.data.model.NewsResponse
import com.mostafa.domain.helper.Response

object TestDataGenerator {

    val newsList = listOf(
        NewsRemote(1, "title-1", "abstract-1", "1-1-2024", "", "", emptyList()),
        NewsRemote(2, "title-2", "abstract-1", "1-1-2024", "", "", emptyList()),
        NewsRemote(3, "title-3", "abstract-1", "1-1-2024", "", "", emptyList()),
    )

    val newsResponse = NewsResponse(newsList)

    val newsSuccessResponse = Response.Success(newsList)

    val errorMessage = "API_ERROR"
}