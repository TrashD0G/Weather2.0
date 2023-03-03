package com.artem.weatherapp.data.api

import com.artem.weatherapp.data.mappers.WeatherApiResponseMapper
import com.artem.weatherapp.domain.models.DomainCityWeatherModel
import retrofit2.HttpException
import java.io.IOException


class ApiRequestImp(
    private val api: ApiOpenWeatherMap,
    private val mapper: WeatherApiResponseMapper
) : ApiRequests {

    private val TOKEN: String = "2c94931ab9b80fa4b073d54ac3e543e8"
    private val UNITS: String = "metric"

    override suspend fun getRequest(cityName: String): ApiResult<DomainCityWeatherModel> {

        return try {
            val response = api.getWeather(token = TOKEN, units = UNITS, city = cityName)
            ApiResult.Success(data = mapper.toDataCityWeatherModel(response.body()!!))
        } catch (e: HttpException) {
            return ApiResult.Error(exception = e)
        } catch (e: IOException) {
            return ApiResult.Error(exception = e)
        } catch (e: Exception) {
            return ApiResult.Error(exception = e)
        }
    }
}