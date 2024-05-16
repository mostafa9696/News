package com.mostafa.domain

import com.mostafa.domain.helper.Response
import com.mostafa.domain.model.NewsModel

object TestDataGenerator {

    val newsList = listOf(
        NewsModel(1, "title-1", "abstract-1", "1-1-2024", "", "", emptyList()),
        NewsModel(2, "title-2", "abstract-1", "1-1-2024", "", "", emptyList()),
        NewsModel(3, "title-3", "abstract-1", "1-1-2024", "", "", emptyList()),
    )

    val newsSuccessResponse = Response.Success(newsList)

    val errorMessage = "API_ERROR"
}