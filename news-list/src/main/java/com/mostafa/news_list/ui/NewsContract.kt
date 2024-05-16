package com.mostafa.news_list.ui

import com.mostafa.base.model.NewsPresentation
import com.mostafa.base.mvi.UiEvent
import com.mostafa.base.mvi.UiState

class NewsContract {

    sealed class Event : UiEvent {
        data object OnLoadNewsList : Event()
    }

    data class State(
        val newsState: NewsUiState,
    ) : UiState


    sealed class NewsUiState {
        data object Idle : NewsUiState()
        data class Loading(val isLoading: Boolean) : NewsUiState()
        data class DisplayNews(val news: List<NewsPresentation>) : NewsUiState()
        data class DisplayError(val errorMessage: String? = null) : NewsUiState()
    }

}