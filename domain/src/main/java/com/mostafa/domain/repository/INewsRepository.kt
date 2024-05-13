package com.mostafa.domain.repository

import com.mostafa.domain.helper.Response
import com.mostafa.domain.model.NewsModel

interface INewsRepository {

    suspend fun getNews(): Response<List<NewsModel>>
}