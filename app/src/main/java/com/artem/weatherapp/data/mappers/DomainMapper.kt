package com.artem.weatherapp.data.mappers


import com.artem.weatherapp.data.api.DataCityWeatherModel

import com.artem.weatherapp.domain.models.DomainCityWeatherModel

class DomainMapper {
    fun toDomainCityWeatherModel(responseResult: DataCityWeatherModel): DomainCityWeatherModel {
        return DomainCityWeatherModel(
            cityName = responseResult.cityName,
            temp = responseResult.temp,
            time = responseResult.time,
            feelsLike = responseResult.feelsLike,
            pressure = responseResult.pressure,
            humidity = responseResult.humidity,
            windSpeed = responseResult.windSpeed,
            weather = responseResult.weather
        )
    }
}