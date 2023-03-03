package com.artem.weatherapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artem.weatherapp.domain.models.DomainCityWeatherModel


class SharedViewModel : ViewModel() {
    private var _sharedData = MutableLiveData<DomainCityWeatherModel>()
    val sharedData: LiveData<DomainCityWeatherModel> get() = _sharedData

    fun shareData(cityData: DomainCityWeatherModel) {
        _sharedData.value = cityData
    }
}