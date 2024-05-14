package com.mostafa.data.mapper

import com.mostafa.data.model.NewsRemote
import com.mostafa.domain.model.ImageModel
import com.mostafa.domain.model.NewsModel

internal fun NewsRemote.toNewsDomain(): NewsModel {
    return NewsModel(
        id = id,
        title = title,
        abstract = abstract,
        byline = byline,
        url = url,
        publishedDate = publishedDate,
        images = getImagesUrl().map { ImageModel(it) }
    )
}