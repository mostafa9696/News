package com.mostafa.news.di

import com.mostafa.data.remote.datasource.INewsDataSource
import com.mostafa.data.remote.datasource.NewsDataSource
import com.mostafa.data.repository.NewsRepository
import com.mostafa.domain.repository.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindNewsRepository(newsRepository: NewsRepository): INewsRepository

    @Binds
    abstract fun bindNewsDataSource(newsRepository: NewsDataSource): INewsDataSource
}