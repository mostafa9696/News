package com.mostafa.news_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mostafa.domain.helper.Response
import com.mostafa.domain.usecases.GetNewsUseCase
import com.mostafa.news_list.mapper.toNewsPresentation
import com.mostafa.news_list.model.NewsPresentation
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
                    Log.d("ee9", response.errorMessage)
            }.onFailure {
                Log.d("ee9", it.message.toString())
            }
        }
    }
}

data class NewsUiState(
    val isLoading: Boolean = true,
    val news: List<NewsPresentation> = emptyList(),
    val message: String? = null,
)