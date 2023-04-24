package com.example.weather.network.common.converters


import com.example.weather.network.common.model.ListWeatherModel
import com.example.weather.usecases.common.DailyWeather
import java.text.SimpleDateFormat
import java.util.*

private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

fun List<ListWeatherModel>.mapToDisplayModel(): List<DailyWeather> {
    return this
        .groupBy { it.toDailyWeather().date.day}
        .map {
            toListDailyWeather(it)
        }
}

private fun toListDailyWeather(
    it: Map.Entry<Int, List<ListWeatherModel>>
) = DailyWeather(
    date = simpleDateFormat.parse(it.value.first().dt_txt)!!,
    minTemp = it.value.minOfOrNull { it.main.temp_min }?.toInt() ?: 0,
    maxTemp = it.value.maxOfOrNull { it.main.temp_max }?.toInt() ?: 0,
    icon = it.value.first().weather.first().icon,
    hoursList = it.value.mapToHoursDisplayModel()
)

private fun ListWeatherModel.toDailyWeather() = DailyWeather(
    date = simpleDateFormat.parse(dt_txt)!!,
    minTemp = listOf(this.main.temp_min).minOrNull()?.toInt() ?: 0,
    maxTemp = listOf(this.main.temp_max).maxOrNull()?.toInt() ?: 0,
    icon = weather.first().icon,
    hoursList = listOf(toTimeWeather())
)
