package com.artem.weatherapp.domain.repository


import com.artem.weatherapp.data.api.ApiResult
import com.artem.weatherapp.data.storage.LocalCityWeatherModel
import com.artem.weatherapp.domain.models.DomainCityWeatherModel


interface WeatherRepository {
    suspend fun localSaveCityWeather(saveParam: LocalCityWeatherModel)
    suspend fun localGetCityWeather(getParam: String): LocalCityWeatherModel
    suspend fun remoteGetCityWeather(getParam: String): ApiResult<DomainCityWeatherModel>
    suspend fun getRowFromDB(): String
    suspend fun searchCityInTable(cityName:String): LocalCityWeatherModel?
    suspend fun localDeleteAllTablesInDb()
}