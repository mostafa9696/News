package com.mostafa.data.remote.datasource

import com.mostafa.data.remote.NewsApi
import com.mostafa.data.remote.helper.apiCall
import javax.inject.Inject

class NewsDataSource @Inject constructor(
    private val api: NewsApi
) : INewsDataSource {
    override suspend fun getNews()=
        apiCall {
            api.getNewsArticles().news
        }
}