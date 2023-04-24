package com.example.weather.network.common.model

import com.google.gson.annotations.SerializedName

data class CityWeatherModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("coord")
    val coord: CoordinationModel,
    @SerializedName("country")
    val country: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int
)
