package com.example.weather.usecases.weatherloader

import com.example.weather.usecases.common.Weather
import io.reactivex.Single

interface WeatherLoader {
    fun getWeather(cityName: String): Single<Weather>
}