package com.mostafa.data.mapper

import com.mostafa.data.model.NewsRemote
import com.mostafa.domain.model.NewsModel

internal fun NewsRemote.toNewsDomain(): NewsModel {
    return NewsModel(
        publishedDate = publishedDate
    )
}