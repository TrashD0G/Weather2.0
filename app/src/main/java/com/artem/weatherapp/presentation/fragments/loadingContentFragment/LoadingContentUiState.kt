package com.artem.weatherapp.presentation.fragments.loadingContentFragment


sealed interface LoadingContentUiState {
    data class Loading(val isLoading: Boolean) : LoadingContentUiState

    object Success : LoadingContentUiState

    data class Error(
        val throwable: String
    ) : LoadingContentUiState
}