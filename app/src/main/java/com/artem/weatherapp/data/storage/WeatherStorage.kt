package com.artem.weatherapp.data.storage



interface WeatherStorage {

    suspend fun save(cityWeather: LocalCityWeatherModel)

    suspend fun findByName(cityName: String): LocalCityWeatherModel?

    suspend fun deleteTableInDb()

    suspend fun getCityNameInRow(): String

    suspend fun get(cityName: String): LocalCityWeatherModel




    // Показывать информацию что идёт запрос (Пока идёт запрос заблокировать кнопку поиска?)
    // Показывать что идёт Загрузка


}