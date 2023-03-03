package com.artem.weatherapp.data.mappers


import com.artem.weatherapp.data.storage.LocalCityWeatherModel
import com.artem.weatherapp.domain.models.DomainCityWeatherModel

class DomainCityWeatherMapper {
    fun toLocalCityWeatherModel(cityWeatherDomain: DomainCityWeatherModel): LocalCityWeatherModel {
        return LocalCityWeatherModel(
            cityName = cityWeatherDomain.cityName,
            temp = cityWeatherDomain.temp,
            time = cityWeatherDomain.time,
            feelsLike = cityWeatherDomain.feelsLike,
            pressure = cityWeatherDomain.pressure,
            humidity = cityWeatherDomain.humidity,
            windSpeed = cityWeatherDomain.windSpeed,
            weather = cityWeatherDomain.weather
        )
    }

    fun toDomainCityWeatherModel(cityWeatherLocal: LocalCityWeatherModel): DomainCityWeatherModel{
        return DomainCityWeatherModel(
            cityName = cityWeatherLocal.cityName,
            temp = cityWeatherLocal.temp,
            time = cityWeatherLocal.time,
            feelsLike = cityWeatherLocal.feelsLike,
            pressure = cityWeatherLocal.pressure,
            humidity = cityWeatherLocal.humidity,
            windSpeed = cityWeatherLocal.windSpeed,
            weather = cityWeatherLocal.weather
        )
    }
}