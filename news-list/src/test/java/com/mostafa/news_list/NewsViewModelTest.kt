package com.mostafa.news_list

import com.mostafa.domain.helper.Response
import com.mostafa.domain.usecases.GetNewsUseCase
import com.mostafa.news_list.ui.NewsContract
import com.mostafa.news_list.ui.NewsViewModel
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
open class NewsViewModelTest {

    /**
     * A test rule to allow testing coroutines that use the main dispatcher
     */
    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var newsUseCase: GetNewsUseCase

    private lateinit var viewmodel: NewsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewmodel = NewsViewModel(
            newsUseCase
        )
    }

    @Test
    fun test_initialState() {
        val state = viewmodel.uiState.value

        assertTrue(state.newsState is NewsContract.NewsUiState.Idle)
    }

    @Test
    fun test_getNews_success() = runTest {
        val expectedSuccessNews = TestDataGenerator.newsSuccessResponse
        val expectedNewsModels = TestDataGenerator.newsList
        Mockito.`when`(newsUseCase.invoke()).thenReturn(expectedSuccessNews)

        viewmodel.setEvent(NewsContract.Event.OnLoadNewsList)

        verify(newsUseCase, times(1)).invoke()

        assertTrue(viewmodel.uiState.value.newsState is NewsContract.NewsUiState.DisplayNews)

        val actualNewsPresentation =
            (viewmodel.uiState.value.newsState as NewsContract.NewsUiState.DisplayNews).news

        assertNotNull(actualNewsPresentation)
        assertEquals(expectedNewsModels.size, actualNewsPresentation.size)

        // test mapping
        Assert.assertEquals(expectedNewsModels[0].id, actualNewsPresentation[0].id)
        Assert.assertEquals(expectedNewsModels[0].url, actualNewsPresentation[0].url)
        Assert.assertEquals(expectedNewsModels[0].byline, actualNewsPresentation[0].byline)
        Assert.assertEquals(expectedNewsModels[0].title, actualNewsPresentation[0].title)
        Assert.assertEquals(
            expectedNewsModels[0].publishedDate,
            actualNewsPresentation[0].publishedDate
        )
        Assert.assertEquals(
            expectedNewsModels[0].images[0].url,
            actualNewsPresentation[0].thumbnail
        )
        Assert.assertEquals(expectedNewsModels[0].images[1].url, actualNewsPresentation[0].poster)
    }

    @Test
    fun test_getNews_error() = runTest {

        Mockito.`when`(newsUseCase.invoke())
            .thenReturn(Response.Error(TestDataGenerator.errorMessage))

        viewmodel.setEvent(NewsContract.Event.OnLoadNewsList)

        val newsState = viewmodel.uiState.value.newsState

        Assert.assertTrue(newsState is NewsContract.NewsUiState.DisplayError)

        Assert.assertEquals(
            (newsState as NewsContract.NewsUiState.DisplayError).errorMessage,
            TestDataGenerator.errorMessage
        )
    }
}