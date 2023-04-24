package com.example.weather.usecases.weatherlocation

import com.example.weather.usecases.common.WeatherLocation
import io.reactivex.Single

interface LocationService {
    fun getLocation(): Single<WeatherLocation>
}