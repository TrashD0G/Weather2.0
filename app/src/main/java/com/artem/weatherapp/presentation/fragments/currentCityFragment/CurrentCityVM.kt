package com.artem.weatherapp.presentation.fragments.currentCityFragment


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.weatherapp.data.storage.LocalCityWeatherModel
import com.artem.weatherapp.domain.models.DomainCityWeatherModel
import com.artem.weatherapp.domain.usecase.LocalGetCityWeatherUseCase
import com.artem.weatherapp.domain.usecase.LocalSaveCityWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentCityVM @Inject constructor(
    private val localSaveCityWeatherUseCase: LocalSaveCityWeatherUseCase,
    private val localGetCityWeatherUseCase: LocalGetCityWeatherUseCase,
) : ViewModel() {

    private var _localCityData = MutableLiveData<Boolean>()
    val localCityData: LiveData<Boolean> get() = _localCityData

    private val _uiState = MutableStateFlow(CurrentCityUiState())
    val uiState: StateFlow<CurrentCityUiState> = _uiState


    fun localSaveCityWeather(param: DomainCityWeatherModel) {
        println("City weather save in DB!")
        viewModelScope.launch {
            try {
                localSaveCityWeatherUseCase.execute(domainCityWeatherModel = param)
            } catch (e:Exception){
                println("Save database Error!: $e")
            }
        }
    }

    fun localGetCityWeather(param: LocalCityWeatherModel) {
        println("City weather get from DB!")
    }
}