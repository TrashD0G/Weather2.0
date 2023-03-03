package com.artem.weatherapp.presentation.utilities

import android.content.Context
import com.artem.weatherapp.data.db.DbHelper
import com.artem.weatherapp.domain.repository.WeatherRepository
import java.io.File
import javax.inject.Inject

class DbHelperImpl @Inject constructor(private val context: Context, private val weatherRepository: WeatherRepository) :DbHelper {

    override fun checkDbExist(): Boolean {
        val dbFile: File = context.getDatabasePath("weather_database")
        return dbFile.exists()
    }

    override suspend fun getRowFromDB(): String {
        return weatherRepository.getRowFromDB()
    }


}