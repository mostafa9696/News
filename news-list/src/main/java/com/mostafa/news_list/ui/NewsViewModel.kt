package com.mostafa.news_list.ui

import androidx.lifecycle.viewModelScope
import com.mostafa.base.mvi.BaseViewModel
import com.mostafa.domain.helper.Response
import com.mostafa.domain.usecases.GetNewsUseCase
import com.mostafa.news_list.mapper.toNewsPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase,
) : BaseViewModel<NewsContract.Event, NewsContract.State>() {

    override fun createInitialState(): NewsContract.State {
        return NewsContract.State(
            NewsContract.NewsUiState.Idle,
        )
    }

    override fun handleEvent(event: NewsContract.Event) {
        when (event) {
            is NewsContract.Event.OnLoadNewsList -> {
                getNews()
            }
        }
    }

    private fun getNews() {
        viewModelScope.launch {
            kotlin.runCatching {
                setState { copy(newsState = NewsContract.NewsUiState.Loading(true)) }
                newsUseCase.invoke()
            }.onSuccess { response ->
                //setState { copy(newsState = NewsContract.NewsUiState.Loading(false)) }

                if (response is Response.Success) {
                    val newsPresentation = response.data.map { newsModel ->
                        newsModel.toNewsPresentation()
                    }
                    setState {
                        copy(
                            newsState = NewsContract.NewsUiState.DisplayNews(newsPresentation),
                        )
                    }
                } else if (response is Response.Error)
                    setErrorState(response.errorMessage)
            }.onFailure {
                setErrorState(it.message ?: "Error occurred, please try again")
            }
        }
    }

    private fun setErrorState(errorMessage: String) {
       // setState { copy(newsState = NewsContract.NewsUiState.Loading(false)) }
        setState { copy(newsState = NewsContract.NewsUiState.DisplayError(errorMessage)) }

    }
}

