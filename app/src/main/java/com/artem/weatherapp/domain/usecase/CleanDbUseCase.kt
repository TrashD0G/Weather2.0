package com.artem.weatherapp.domain.usecase


import com.artem.weatherapp.domain.repository.WeatherRepository

class CleanDbUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke() = weatherRepository.localDeleteAllTablesInDb()
}