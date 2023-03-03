package com.artem.weatherapp.domain.models


data class DomainCityWeatherModel(
    var cityName: String,
    var temp: String,
    var time: String,
    var feelsLike: String,
    var pressure: String,
    var humidity: String,
    var windSpeed: String,
    var weather: String
)

