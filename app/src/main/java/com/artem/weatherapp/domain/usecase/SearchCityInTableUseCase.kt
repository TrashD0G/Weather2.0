package com.artem.weatherapp.domain.usecase

import com.artem.weatherapp.domain.repository.WeatherRepository

class SearchCityInTableUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(param: String) =  weatherRepository.searchCityInTable(param)
}