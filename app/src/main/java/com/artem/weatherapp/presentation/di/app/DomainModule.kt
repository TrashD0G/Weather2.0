package com.artem.weatherapp.presentation.di.app

import com.artem.weatherapp.data.db.DbHelper
import com.artem.weatherapp.data.mappers.DomainCityWeatherMapper
import com.artem.weatherapp.domain.repository.WeatherRepository
import com.artem.weatherapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideSaveCityWeatherUseCase(weatherRepository: WeatherRepository, mapper: DomainCityWeatherMapper): LocalSaveCityWeatherUseCase {
        return LocalSaveCityWeatherUseCase(weatherRepository = weatherRepository, mapper = mapper)
    }

    @Provides
    fun getCityWeatherUseCase(weatherRepository: WeatherRepository, mapper: DomainCityWeatherMapper): LocalGetCityWeatherUseCase {
        return LocalGetCityWeatherUseCase(weatherRepository = weatherRepository, mapper = mapper)
    }

    @Provides
    fun remoteGetCityWeatherUseCase(weatherRepository: WeatherRepository): RemoteGetCityWeatherUseCase{
        return RemoteGetCityWeatherUseCase(weatherRepository = weatherRepository)
    }

    @Provides
    fun checkDbExistUseCase(dbHelper: DbHelper): CheckDbExistsUseCase{
        return CheckDbExistsUseCase(dbHelper = dbHelper)
    }

    @Provides
    fun getRowCountInDB(dbHelper: DbHelper): GetRowCountInDbUseCase{
        return GetRowCountInDbUseCase(dbHelper = dbHelper)
    }

    @Provides
    fun searchCityInTableUseCase(weatherRepository: WeatherRepository): SearchCityInTableUseCase{
        return SearchCityInTableUseCase(weatherRepository = weatherRepository)
    }

    @Provides
    fun deleteDbUseCase(weatherRepository: WeatherRepository): CleanDbUseCase{
        return CleanDbUseCase(weatherRepository = weatherRepository)
    }

}