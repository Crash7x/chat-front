package com.example.weather.usecases.weatherlocation

import com.example.weather.usecases.common.Weather
import com.example.weather.usecases.common.WeatherLocation
import io.reactivex.Single

interface LocationDataSource {
    fun getWeatherLocation(location: WeatherLocation): Single<Weather>
}