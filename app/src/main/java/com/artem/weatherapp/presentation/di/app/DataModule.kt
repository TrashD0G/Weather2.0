package com.artem.weatherapp.presentation.di.app



import com.artem.weatherapp.data.api.ApiRequests
import com.artem.weatherapp.data.db.WeatherDAO
import com.artem.weatherapp.data.mappers.DomainCityWeatherMapper
import com.artem.weatherapp.data.repository.WeatherRepositoryImpl
import com.artem.weatherapp.data.storage.LocalWeatherStorage
import com.artem.weatherapp.data.storage.WeatherStorage
import com.artem.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Provides
    @Singleton
    fun provideWeatherStorage(weatherDAO: WeatherDAO): WeatherStorage {
        return LocalWeatherStorage(weatherDAO = weatherDAO)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(apiRequest: ApiRequests, weatherStorage: WeatherStorage): WeatherRepository {
        return WeatherRepositoryImpl(apiRequestsImp = apiRequest, localWeatherStorage = weatherStorage)
    }

    @Provides
    fun provideDomainCityWeatherMapper(): DomainCityWeatherMapper{
        return DomainCityWeatherMapper()
    }
}