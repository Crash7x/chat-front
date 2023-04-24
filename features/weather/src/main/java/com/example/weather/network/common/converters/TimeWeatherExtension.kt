package com.example.weather.network.common.converters

import com.example.weather.network.common.model.ListWeatherModel
import com.example.weather.usecases.common.TimeWeather
import java.text.SimpleDateFormat
import java.util.*

private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

fun List<ListWeatherModel>.mapToHoursDisplayModel(): List<TimeWeather> {
    return this
        .map {
            it.toTimeWeather()
        }
}

fun ListWeatherModel.toTimeWeather(): TimeWeather = TimeWeather(
    timeHours = simpleDateFormat.parse(dt_txt)!!,
    tempHours = main.temp.toInt(),
    iconHours = weather.first().icon
)