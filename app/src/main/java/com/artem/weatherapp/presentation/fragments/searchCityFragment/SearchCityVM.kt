package com.artem.weatherapp.presentation.fragments.searchCityFragment


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.weatherapp.data.api.ApiResult
import com.artem.weatherapp.domain.models.DomainCityWeatherModel
import com.artem.weatherapp.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchCityVM @Inject constructor(
    private val remoteGetCityWeatherUseCase: RemoteGetCityWeatherUseCase,
    private val validationInputUseCase: ValidationInputUseCase
) : ViewModel() {

    private var _cityData = MutableLiveData<DomainCityWeatherModel>()
    val cityData: LiveData<DomainCityWeatherModel> get() = _cityData


    private val _uiState = MutableStateFlow<SearchCityUiState>(
        SearchCityUiState.Loading(isLoading = false)
    )
    val uiState: StateFlow<SearchCityUiState> = _uiState


    fun remoteGetCityWeather(cityName: String) {
        val resultInput: Boolean = validationInputUseCase.invoke(cityName)

        viewModelScope.launch {
            if (resultInput) {
                val cityNameValid = cityName.trim()
                val resultRequest: Boolean

                try {
                    _uiState.update {
                        SearchCityUiState.Loading(true)
                    }
                    resultRequest = getCityWeather(cityNameValid)
                    if (resultRequest) {
                        Log.d("Debug", "SearchCityVM: uiState After update: ${uiState.value}")
                        _uiState.update {
                            SearchCityUiState.Success
                        }
                        Log.d("Debug", "SearchCityVM: uiState Before update: ${uiState.value}")

                    } else {
                        _uiState.update {
                            SearchCityUiState.Error("Ошибка запроса test!")
                        }
                    }

                } catch (e: Exception) {
                    _uiState.update {
                        SearchCityUiState.Error("Ошибка запроса! $e")
                    }
                }

            } else {
                _uiState.update {
                    SearchCityUiState.Error("Ошибка ввода!")
                }
            }
        }
    }


    private suspend fun getCityWeather(cityName: String): Boolean {
        return when (val result =
            withContext(Dispatchers.IO) { remoteGetCityWeatherUseCase(param = cityName) }) {

            is ApiResult.Success -> {
                Log.d("Debug", "SearchCityVM: Result - ${result.data.cityName}")
                _cityData.value = result.data
                true
            }

            is ApiResult.Error -> {
                Log.d("Debug", "SearchCityVM: Error - ${result.exception.message}")
                false
            }
        }
    }


    fun errorMessageShown() {
        _uiState.update {
            SearchCityUiState.Loading(isLoading = false)
        }
        Log.d("Debug", "UiState BEFORE errorMessage: ${_uiState.value}")
    }
}