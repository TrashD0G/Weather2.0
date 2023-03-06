package com.artem.weatherapp.presentation.fragments.currentCityFragment

import android.content.Context
import android.util.Log
import androidx.work.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*


class UpdateWeatherWorker(private val appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val sdf = SimpleDateFormat("HH:mm:ss z")
        val currentTime: String = sdf.format(Date())
        Log.d("NetworkStatusDebug", "Worker work!")
        Log.d("NetworkStatusDebug", "Time: $currentTime")
        return@withContext Result.success()
    }
}

