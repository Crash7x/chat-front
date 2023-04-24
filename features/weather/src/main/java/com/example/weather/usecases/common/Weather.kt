package com.example.weather.usecases.common

import com.example.core.models.Icon
import java.util.*

data class Weather(
    val headerWeather: List<TodayWeather>,
    val dailyWeather: List<DailyWeather>,
    val cityName: String
)

data class TodayWeather(
    val date: Date,
    var icon: Icon,
    val temp: Int,
    val description: String,
)

data class DailyWeather(
    val date: Date,
    val minTemp: Int,
    val maxTemp: Int,
    val icon: Icon,
    val hoursList: List<TimeWeather>
)

data class TimeWeather(
    var timeHours: Date,
    val tempHours: Int,
    val iconHours: Icon
)