package com.artem.weatherapp.presentation.fragments.currentCityFragment

sealed interface NetworkStatus{
    object Available : NetworkStatus
    object Unavailable: NetworkStatus
    object Lost : NetworkStatus
    object NotConnected : NetworkStatus
}