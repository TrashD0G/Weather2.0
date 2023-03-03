package com.artem.weatherapp.data.api


data class DataCityWeatherModel(
    var cityName: String,
    var temp: String,
    var time: String,
    var feelsLike: String,
    var pressure: String,
    var humidity: String,
    var windSpeed: String,
    var weather: String
)