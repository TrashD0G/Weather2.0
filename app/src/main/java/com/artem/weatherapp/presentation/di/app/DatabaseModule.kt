package com.artem.weatherapp.presentation.di.app

import android.content.Context
import androidx.room.Room
import com.artem.weatherapp.data.db.DbHelper
import com.artem.weatherapp.data.db.WeatherDatabase
import com.artem.weatherapp.domain.repository.WeatherRepository
import com.artem.weatherapp.presentation.utilities.DbHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    @Provides
    @Singleton
    fun provideWeatherDatabase(
        @ApplicationContext appContext: Context
    ): WeatherDatabase {
        return Room.databaseBuilder(appContext, WeatherDatabase::class.java, "weather_database")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun provideWeatherDAO(database: WeatherDatabase) = database.getWeatherDao()

    @Provides
    @Singleton
    fun provideDbHelper(@ApplicationContext context: Context, weatherRepository: WeatherRepository):DbHelper{
        return DbHelperImpl(context = context, weatherRepository = weatherRepository)
    }

}