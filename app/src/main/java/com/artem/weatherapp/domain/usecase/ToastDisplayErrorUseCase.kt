package com.artem.weatherapp.domain.usecase


import com.artem.weatherapp.presentation.utilities.AppUtilities

class ToastDisplayErrorUseCase(private val appUtilities: AppUtilities) {
    operator fun invoke(message: String) = appUtilities.toastDisplayError(message)
}