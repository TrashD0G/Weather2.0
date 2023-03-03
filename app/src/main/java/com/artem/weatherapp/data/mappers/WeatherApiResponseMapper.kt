package com.artem.weatherapp.data.mappers


import com.artem.weatherapp.data.api.WeatherApiResponse
import com.artem.weatherapp.domain.models.DomainCityWeatherModel


class WeatherApiResponseMapper {
    fun toDataCityWeatherModel(response: WeatherApiResponse): DomainCityWeatherModel {
        return DomainCityWeatherModel(
            cityName = response.name,
            temp = response.main!!.temp.toString(),
            time = response.dt.toString(),
            feelsLike = response.main!!.feels_like.toString(),
            pressure = response.main!!.pressure.toString(),
            humidity = response.main!!.humidity.toString(),
            windSpeed = response.wind!!.speed.toString(),
            weather = response.weather.toString()
        )
    }
}