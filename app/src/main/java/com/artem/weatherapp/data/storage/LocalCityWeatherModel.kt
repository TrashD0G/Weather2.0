package com.artem.weatherapp.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_weather")
data class LocalCityWeatherModel(
    @PrimaryKey var cityName: String,
    var temp: String,
    var time: String,
    var feelsLike: String,
    var pressure: String,
    var humidity: String,
    var windSpeed: String,
    var weather: String
)
