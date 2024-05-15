package com.mostafa.news_list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mostafa.base.model.NewsPresentation
import com.mostafa.domain.helper.Response
import com.mostafa.domain.usecases.GetNewsUseCase
import com.mostafa.news_list.mapper.toNewsPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            kotlin.runCatching {
                newsUseCase.invoke()
            }.onSuccess { response ->
                if (response is Response.Success)
                    _uiState.update {
                        it.copy(
                            news = response.data.map { newsModel ->
                                newsModel.toNewsPresentation()
                            },
                            isLoading = false
                        )
                    }
                else if (response is Response.Error)
                    setErrorState(response.errorMessage)
            }.onFailure {
                setErrorState(it.message ?: "Error occurred, please try again")
            }
        }
    }

    private fun setErrorState(errorMessage: String) {
        _uiState.update {
            it.copy(
                errorMessage = errorMessage,
                isLoading = false
            )
        }
    }

    fun consumedUserMessage() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
}

data class NewsUiState(
    val isLoading: Boolean = true,
    val news: List<NewsPresentation> = emptyList(),
    val errorMessage: String? = null,
)