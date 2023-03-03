package com.artem.weatherapp.domain.usecase

import com.artem.weatherapp.data.db.DbHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRowCountInDbUseCase @Inject constructor(private val dbHelper: DbHelper) {
    suspend operator fun invoke() = dbHelper.getRowFromDB()
}