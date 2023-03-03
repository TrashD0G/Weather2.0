package com.artem.weatherapp.domain.usecase

import com.artem.weatherapp.data.mappers.DomainCityWeatherMapper
import com.artem.weatherapp.data.storage.LocalCityWeatherModel
import com.artem.weatherapp.domain.models.DomainCityWeatherModel
import com.artem.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject


class LocalGetCityWeatherUseCase @Inject constructor(private val weatherRepository: WeatherRepository, private val mapper: DomainCityWeatherMapper) {
    suspend fun execute(cityName: String): DomainCityWeatherModel{
       return mapper.toDomainCityWeatherModel(weatherRepository.localGetCityWeather(cityName))
    }
}