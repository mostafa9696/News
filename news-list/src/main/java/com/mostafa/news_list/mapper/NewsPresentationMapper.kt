package com.mostafa.news_list.mapper

import com.mostafa.domain.model.NewsModel
import com.mostafa.news_list.model.NewsPresentation

internal fun NewsModel.toNewsPresentation(): NewsPresentation {
    return NewsPresentation(
        id = id,
        title = title,
        abstract = abstract,
        byline = byline,
        url = url,
        publishedDate = publishedDate,
        thumbnail = if(images.isNotEmpty()) images.first().url else "",
        poster = if(images.isNotEmpty()) images.last().url  else ""
    )
}