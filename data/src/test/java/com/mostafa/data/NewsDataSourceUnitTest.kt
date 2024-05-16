package com.mostafa.data

import com.mostafa.data.remote.NewsApi
import com.mostafa.data.remote.datasource.INewsDataSource
import com.mostafa.data.remote.datasource.NewsDataSource
import com.mostafa.domain.helper.Response
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.exceptions.base.MockitoException

@RunWith(JUnit4::class)
class NewsDataSourceUnitTest {

    @Mock
    private lateinit var newsApi: NewsApi

    private lateinit var newsDataSource: INewsDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        newsDataSource = NewsDataSource(
            newsApi
        )
    }

    @Test
    fun test_getNews_success() = runTest {
        val expectedNewsResponse = TestDataGenerator.newsResponse



        Mockito.`when`(newsApi.getNewsArticles(any(), any()))
            .thenReturn(expectedNewsResponse)

        val actualNewsResponse = newsDataSource.getNews()

        verify(newsApi, times(1)).getNewsArticles()

        Assert.assertNotNull(actualNewsResponse)
        Assert.assertTrue(actualNewsResponse is Response.Success)
        Assert.assertEquals(
            expectedNewsResponse.news.size,
            (actualNewsResponse as Response.Success).data.size
        )
    }

    @Test
    fun test_getNews_error() = runTest {

        Mockito.`when`(newsApi.getNewsArticles(any(), any()))
            .thenThrow(MockitoException(TestDataGenerator.errorMessage))

        val actualNewsResponse = newsDataSource.getNews()

        Assert.assertNotNull(actualNewsResponse)
        Assert.assertTrue(actualNewsResponse is Response.Error)
        Assert.assertEquals(
            TestDataGenerator.errorMessage,
            (actualNewsResponse as Response.Error).errorMessage,
        )
    }

}