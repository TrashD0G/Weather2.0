package com.artem.weatherapp.presentation.fragments.searchCityFragment


sealed interface SearchCityUiState {

    data class Loading(val isLoading: Boolean) : SearchCityUiState

    object Success :
        SearchCityUiState

    data class Error(
        val exception: String
    ) : SearchCityUiState
}