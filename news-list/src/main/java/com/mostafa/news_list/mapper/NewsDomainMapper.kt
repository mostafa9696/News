package com.mostafa.news_list.mapper

import com.mostafa.domain.model.NewsModel
import com.mostafa.news_list.model.NewsPresentation

internal fun NewsModel.toNewsPresentation(): NewsPresentation {
    return NewsPresentation(
        publishedDate = publishedDate
    )
}