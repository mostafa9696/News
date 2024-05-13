package com.mostafa.data.repository

import com.mostafa.data.mapper.mapResponse
import com.mostafa.data.mapper.toNewsDomain
import com.mostafa.data.remote.datasource.INewsDataSource
import com.mostafa.domain.repository.INewsRepository
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val dataSource: INewsDataSource,
) : INewsRepository {
    override suspend fun getNews() = dataSource.getNews().mapResponse { news ->
        news.map {
            it.toNewsDomain()
        }
    }
}