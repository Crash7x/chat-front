package com.example.cities

import com.example.core.models.Icon

data class CityElement(
    val cityName: String,
    val minimumTemperature: Int,
    val maximumTemperature: Int,
    val weatherIcon: Icon
)