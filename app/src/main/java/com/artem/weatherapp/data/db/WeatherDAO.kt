package com.artem.weatherapp.data.db

import androidx.room.*
import com.artem.weatherapp.data.storage.LocalCityWeatherModel

@Dao
interface WeatherDAO {

    @Query("SELECT * FROM city_weather")
    suspend fun getAll(): List<LocalCityWeatherModel>

    @Query("SELECT * FROM city_weather WHERE cityName LIKE :cityName ")
    suspend fun get(cityName: String): LocalCityWeatherModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cityWeather: LocalCityWeatherModel)

    @Query("SELECT cityName FROM city_weather ")
    suspend fun getCityNameFromRow(): String

    @Query("SELECT COUNT(*) FROM city_weather")
    suspend fun getCountRowInTable(): Int

    @Update
    suspend fun update(cityWeather: LocalCityWeatherModel)

    @Delete
    suspend fun delete(cityWeather: LocalCityWeatherModel)

    @Query("DELETE FROM city_weather")
    suspend fun deleteAll()

}