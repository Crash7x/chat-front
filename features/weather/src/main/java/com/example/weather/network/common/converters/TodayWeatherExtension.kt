package com.example.weather.network.common.converters

import com.example.weather.network.common.model.ListWeatherModel
import com.example.weather.usecases.common.TodayWeather
import java.text.SimpleDateFormat
import java.util.*

private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

fun List<ListWeatherModel>.mapToHeaderDisplayModel(): TodayWeather {
    return this
        .map {
            it.toTodayWeather()
        }
        .first()
}

fun ListWeatherModel.toTodayWeather(): TodayWeather = TodayWeather(
    date = simpleDateFormat.parse(dt_txt)!!,
    icon = weather.first().icon,
    temp = main.temp.toInt(),
    description = weather.first().description
)