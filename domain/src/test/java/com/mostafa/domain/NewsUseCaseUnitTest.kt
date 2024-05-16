package com.mostafa.domain


import com.mostafa.domain.helper.Response
import com.mostafa.domain.repository.INewsRepository
import com.mostafa.domain.usecases.GetNewsUseCase
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
class NewsUseCaseUnitTest {

    @Mock
    private lateinit var newsRepository: INewsRepository

    private lateinit var newsUseCase: GetNewsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        newsUseCase = GetNewsUseCase(
            newsRepository
        )
    }

    @Test
    fun test_getNews_success() = runTest {
        val expectedSuccessNews = TestDataGenerator.newsSuccessResponse
        val expectedNewsModels = TestDataGenerator.newsList


        Mockito.`when`(newsRepository.getNews())
            .thenReturn(expectedSuccessNews)

        val actualDomainNewsResponse = newsUseCase.invoke()

        verify(newsRepository, times(1)).getNews()


        Assert.assertNotNull(actualDomainNewsResponse)
        Assert.assertTrue(actualDomainNewsResponse is Response.Success)

        // test mapping
        val actualNewsModels = (actualDomainNewsResponse as Response.Success).data
        Assert.assertEquals(
            expectedSuccessNews.data.size,
            actualNewsModels.size
        )
        Assert.assertEquals(expectedNewsModels[0].id, actualNewsModels[0].id)
        Assert.assertEquals(expectedNewsModels[0].url, actualNewsModels[0].url)
        Assert.assertEquals(expectedNewsModels[0].byline, actualNewsModels[0].byline)
        Assert.assertEquals(expectedNewsModels[0].title, actualNewsModels[0].title)
        Assert.assertEquals(expectedNewsModels[0].publishedDate, actualNewsModels[0].publishedDate)

    }

    @Test
    fun test_getNews_error() = runTest {

        Mockito.`when`(newsRepository.getNews())
            .thenReturn(Response.Error(TestDataGenerator.errorMessage))

        val actualNewsResponse = newsUseCase.invoke()

        Assert.assertNotNull(actualNewsResponse)
        Assert.assertTrue(actualNewsResponse is Response.Error)
        Assert.assertEquals(
            (actualNewsResponse as Response.Error).errorMessage,
            TestDataGenerator.errorMessage
        )
    }

}