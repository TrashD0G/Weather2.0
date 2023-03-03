package com.artem.weatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artem.weatherapp.data.storage.LocalCityWeatherModel


@Database(entities = [LocalCityWeatherModel::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDAO
}