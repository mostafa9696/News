package com.mostafa.data

import com.mostafa.data.remote.datasource.INewsDataSource
import com.mostafa.data.repository.NewsRepository
import com.mostafa.domain.helper.Response
import com.mostafa.domain.repository.INewsRepository
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

@RunWith(JUnit4::class)
class NewsRepositoryUnitTest {

    @Mock
    private lateinit var newsDataSource: INewsDataSource

    private lateinit var newsRepository: INewsRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        newsRepository = NewsRepository(
            newsDataSource
        )
    }

    @Test
    fun test_getNews_success() = runTest {
        val expectedSuccessNews = TestDataGenerator.newsSuccessResponse
        val expectedNewsRemote = TestDataGenerator.newsList


        Mockito.`when`(newsDataSource.getNews())
            .thenReturn(expectedSuccessNews)

        val actualNewsResponse = newsRepository.getNews()

        verify(newsDataSource, times(1)).getNews()


        Assert.assertNotNull(actualNewsResponse)
        Assert.assertTrue(actualNewsResponse is Response.Success)

        // test mapping
        val actualNewsModels = (actualNewsResponse as Response.Success).data
        Assert.assertEquals(
            expectedSuccessNews.data.size,
            actualNewsModels.size
        )
        Assert.assertEquals(expectedNewsRemote[0].id, actualNewsModels[0].id)
        Assert.assertEquals(expectedNewsRemote[0].url, actualNewsModels[0].url)
        Assert.assertEquals(expectedNewsRemote[0].byline, actualNewsModels[0].byline)
        Assert.assertEquals(expectedNewsRemote[0].title, actualNewsModels[0].title)
        Assert.assertEquals(expectedNewsRemote[0].publishedDate, actualNewsModels[0].publishedDate)

    }

    @Test
    fun test_getNews_error() = runTest {

        Mockito.`when`(newsDataSource.getNews())
            .thenReturn(Response.Error(TestDataGenerator.errorMessage))

        val actualNewsResponse = newsDataSource.getNews()

        Assert.assertNotNull(actualNewsResponse)
        Assert.assertTrue(actualNewsResponse is Response.Error)
        Assert.assertEquals(
            (actualNewsResponse as Response.Error).errorMessage,
            TestDataGenerator.errorMessage
        )
    }

}