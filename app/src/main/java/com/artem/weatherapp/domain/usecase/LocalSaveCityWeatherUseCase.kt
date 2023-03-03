package com.artem.weatherapp.domain.usecase

import com.artem.weatherapp.data.mappers.DomainCityWeatherMapper
import com.artem.weatherapp.domain.models.DomainCityWeatherModel
import com.artem.weatherapp.domain.repository.WeatherRepository


class LocalSaveCityWeatherUseCase(private val weatherRepository: WeatherRepository, private val mapper: DomainCityWeatherMapper) {
    suspend fun execute(domainCityWeatherModel: DomainCityWeatherModel){
        weatherRepository.localSaveCityWeather(mapper.toLocalCityWeatherModel(cityWeatherDomain = domainCityWeatherModel))
    }
}