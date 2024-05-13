package com.mostafa.data.remote.datasource

import com.mostafa.data.model.NewsRemote
import com.mostafa.domain.helper.Response

interface INewsDataSource {
    suspend fun getNews(): Response<List<NewsRemote>>
}