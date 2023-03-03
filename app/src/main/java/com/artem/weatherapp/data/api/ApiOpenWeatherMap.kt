package com.artem.weatherapp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiOpenWeatherMap {
    @GET("data/2.5/weather?")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") token: String
    ): Response<WeatherApiResponse>
}