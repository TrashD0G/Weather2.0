package com.artem.weatherapp.data.db

interface DbHelper {
    fun checkDbExist(): Boolean
    suspend fun getRowFromDB(): String
}