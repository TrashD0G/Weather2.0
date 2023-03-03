package com.artem.weatherapp.domain.usecase


import com.artem.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject


class RemoteGetCityWeatherUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(param: String) = weatherRepository.remoteGetCityWeather(param)
}