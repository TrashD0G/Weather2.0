package com.artem.weatherapp.domain.usecase

import com.artem.weatherapp.data.db.DbHelper
import javax.inject.Inject


class CheckDbExistsUseCase @Inject constructor(private val dbHelper: DbHelper) {
    suspend fun execute(): Boolean{
        return dbHelper.checkDbExist()
    }
}