package com.example.weather.network.common

import com.example.weather.network.common.model.WeatherModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

private const val CITY_NAME = "q"
private const val LATITUDE = "lat"
private const val LONGITUDE = "lon"

interface ApiWeatherService {
    @GET("forecast?lang=ru&units=metric&appid=296dbfe3af91642e2df4bf1e68064c26")
    fun getApi(@Query(CITY_NAME) cityName: String): Single<WeatherModel>

    @GET("forecast?lang=ru&units=metric&appid=296dbfe3af91642e2df4bf1e68064c26")
    fun getLocation(
        @Query(LATITUDE) latitude: Double,
        @Query(LONGITUDE) longitude: Double
    ): Single<WeatherModel>
}