package com.artem.weatherapp.domain.usecase


import com.artem.weatherapp.presentation.utilities.AppUtilities


class ValidationInputUseCase(private val appUtilities: AppUtilities) {
     operator fun invoke(cityName:String) = appUtilities.validationInput(cityName)
}