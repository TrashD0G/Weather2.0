package com.artem.weatherapp.presentation.fragments.loadingContentFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.weatherapp.data.api.ApiResult
import com.artem.weatherapp.domain.models.DomainCityWeatherModel
import com.artem.weatherapp.domain.usecase.CheckDbExistsUseCase
import com.artem.weatherapp.domain.usecase.GetRowCountInDbUseCase
import com.artem.weatherapp.domain.usecase.LocalGetCityWeatherUseCase
import com.artem.weatherapp.domain.usecase.RemoteGetCityWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoadingContentVM @Inject constructor(
    private val checkDbExistsUseCase: CheckDbExistsUseCase,
    private val getRowCountInDB: GetRowCountInDbUseCase,
    private val remoteGetCityWeatherUseCase: RemoteGetCityWeatherUseCase,
    private val localGetCityWeatherUseCase: LocalGetCityWeatherUseCase
) : ViewModel() {

    private var _cityData = MutableLiveData<DomainCityWeatherModel>()
    val cityData: LiveData<DomainCityWeatherModel> get() = _cityData

    private val _uiState = MutableStateFlow<LoadingContentUiState>(LoadingContentUiState.Loading(isLoading = false))
    val uiState: StateFlow<LoadingContentUiState> = _uiState


    fun startLoadingContent() {
        viewModelScope.launch {
            _uiState.update {
                LoadingContentUiState.Loading(isLoading = true)
            }
            Log.d("Debug", "LoadingContentVM: Start Loading Content!")
            val resultDbExist = existDB()
            Log.d("Debug", "LoadingContentVM: Result DB Exist - $resultDbExist")
            if (resultDbExist) {

                val resultRowExistInDB = withContext(Dispatchers.IO) { getRowCountInDB() }
                println("Result row exist in DB: $resultRowExistInDB")
                Log.d("Debug", "LoadingContentVM: Result row exist in DB - $resultRowExistInDB")

                if (resultRowExistInDB.isNotEmpty()) {
                    val resultGetRemote = getRemoteCityWeather(resultRowExistInDB)
                    Log.d("Debug", "LoadingContentVM: result get remote - $resultGetRemote")
                    if (resultGetRemote) {
                        _uiState.update {
                            LoadingContentUiState.Success
                        }
                    } else {
                        getLocalCityWeather(resultRowExistInDB)
                        _uiState.update {
                            LoadingContentUiState.Success
                        }
                    }
                } else {
                    _uiState.update {
                        LoadingContentUiState.Error("Flow Error!")
                    }
                }
            } else {
                _uiState.update {
                    LoadingContentUiState.Error("Flow Error!")
                }
            }
        }
    }


    private suspend fun existDB(): Boolean {
        return withContext(Dispatchers.Default) { checkDbExistsUseCase.execute() }
    }


    private suspend fun getLocalCityWeather(cityName: String) {
        val result = withContext(Dispatchers.IO) { localGetCityWeatherUseCase.execute(cityName) }
        _cityData.postValue(result)
    }


    private suspend fun getRemoteCityWeather(cityName: String): Boolean {
        return when (val result =
            withContext(Dispatchers.IO) { remoteGetCityWeatherUseCase(param = cityName) }) {

            is ApiResult.Success -> {
                println("Result: ${result.data.cityName}")
                println("Result: ${result.data.temp}")
                _cityData.postValue(result.data)

                true
            }

            is ApiResult.Error -> {
                println("Error: ${result.exception.message}")
                false
            }
        }
    }
}