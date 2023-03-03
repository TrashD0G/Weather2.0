package com.artem.weatherapp.data.repository


import com.artem.weatherapp.data.api.ApiRequests
import com.artem.weatherapp.data.api.ApiResult
import com.artem.weatherapp.data.storage.LocalCityWeatherModel
import com.artem.weatherapp.data.storage.WeatherStorage
import com.artem.weatherapp.domain.models.DomainCityWeatherModel
import com.artem.weatherapp.domain.repository.WeatherRepository



import javax.inject.Inject



class WeatherRepositoryImpl @Inject constructor(private val apiRequestsImp: ApiRequests, private val localWeatherStorage: WeatherStorage) : WeatherRepository {


    override suspend fun localSaveCityWeather(saveParam: LocalCityWeatherModel) {
        localWeatherStorage.save(cityWeather = saveParam)
    }

    override suspend fun localGetCityWeather(getParam: String): LocalCityWeatherModel {
        return localWeatherStorage.get(getParam)
    }

    override suspend fun remoteGetCityWeather(getParam: String): ApiResult<DomainCityWeatherModel> {
        return apiRequestsImp.getRequest(cityName = getParam)
    }

    override suspend fun getRowFromDB(): String {
        return localWeatherStorage.getCityNameInRow()
    }

    override suspend fun searchCityInTable(cityName: String): LocalCityWeatherModel? {
        return localWeatherStorage.findByName(cityName = cityName)
    }

    override suspend fun localDeleteAllTablesInDb() {
        return localWeatherStorage.deleteTableInDb()
    }
}