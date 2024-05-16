package com.mostafa.news_list

import com.mostafa.base.model.NewsPresentation
import com.mostafa.domain.helper.Response
import com.mostafa.domain.model.ImageModel
import com.mostafa.domain.model.NewsModel

object TestDataGenerator {

    val newsList = listOf(
        NewsModel(
            1, "title-1", "abstract-1", "1-1-2024", "", "",
            listOf(ImageModel("thumb-1"), ImageModel("poster-1"))
        ),
        NewsModel(2, "title-2", "abstract-1", "1-1-2024", "", "", emptyList()),
        NewsModel(3, "title-3", "abstract-1", "1-1-2024", "", "", emptyList()),
    )

    val newsListPresentation = listOf(
        NewsPresentation(1, "title-1", "abstract-1", "1-1-2024", "", "", "thumb-1", "poster-1"),
        NewsPresentation(2, "title-2", "abstract-1", "1-1-2024", "", "", "thumb-1", "poster-1"),
        NewsPresentation(3, "title-3", "abstract-1", "1-1-2024", "", "", "thumb-1", "poster-1"),
    )

    val newsSuccessResponse = Response.Success(newsList)

    val errorMessage = "API_ERROR"
}