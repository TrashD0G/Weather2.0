package com.artem.weatherapp.data.api

import com.artem.weatherapp.domain.models.DomainCityWeatherModel


interface ApiRequests {
    suspend fun getRequest(cityName: String): ApiResult<DomainCityWeatherModel>
}