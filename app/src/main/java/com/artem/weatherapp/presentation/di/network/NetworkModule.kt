package com.artem.weatherapp.presentation.di.network

import com.artem.weatherapp.data.api.ApiOpenWeatherMap
import com.artem.weatherapp.data.api.ApiRequestImp
import com.artem.weatherapp.data.api.ApiRequests
import com.artem.weatherapp.data.mappers.WeatherApiResponseMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesGson(): Gson = GsonBuilder().create()


    @Provides
    fun provideWeatherApiResponseMapper(): WeatherApiResponseMapper{
        return WeatherApiResponseMapper()
    }

    @Provides
    fun providesApiRequestImp(retrofit: ApiOpenWeatherMap, mapper: WeatherApiResponseMapper): ApiRequests {
        return ApiRequestImp(api = retrofit, mapper = mapper)
    }

    @Provides
    @Singleton
    fun providesRetrofitInstance(gson: Gson): ApiOpenWeatherMap {
        return Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiOpenWeatherMap::class.java)
    }

}