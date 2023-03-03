package com.artem.weatherapp.presentation.utilities


interface AppUtilities {

    fun validationInput(checkString: String): Boolean

    //fun currentTime(rawDate: Int): String
    //fun currentWeather(rawWeather: String?, imageWeather: ImageView)
    fun toastDisplayError(message: String)

}