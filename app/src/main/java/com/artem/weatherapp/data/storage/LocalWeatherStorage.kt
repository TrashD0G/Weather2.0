package com.artem.weatherapp.data.storage


import com.artem.weatherapp.data.db.WeatherDAO
import javax.inject.Inject


class LocalWeatherStorage @Inject constructor (private val weatherDAO: WeatherDAO) : WeatherStorage {

    override suspend fun save(cityWeather: LocalCityWeatherModel) {
        weatherDAO.insert(cityWeather = cityWeather)
        println("All entity in DB: ${weatherDAO.getAll()}")
    }

    override suspend fun findByName(cityName: String): LocalCityWeatherModel {
        return weatherDAO.get(cityName = cityName)
    }

    override suspend fun deleteTableInDb() {
        return weatherDAO.deleteAll()
    }

    override suspend fun getCityNameInRow(): String {
        return weatherDAO.getCityNameFromRow()
    }

    override suspend fun get(cityName: String): LocalCityWeatherModel {
        return weatherDAO.get(cityName)
    }
}
