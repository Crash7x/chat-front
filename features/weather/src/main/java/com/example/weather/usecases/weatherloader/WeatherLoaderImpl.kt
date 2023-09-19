package com.example.weather.usecases.weatherloader

import com.example.weather.usecases.common.Weather
import io.reactivex.Single

class WeatherLoaderImpl(private val weatherService: WeatherService) : WeatherLoader {
    override suspend fun getWeather(cityName: String): Weather {
        return weatherService.getWeather(cityName)
    }
}