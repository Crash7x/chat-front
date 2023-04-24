package com.example.weather.network.common.model

import com.google.gson.annotations.SerializedName

data class WindModel(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("gust")
    val gust: Double
)
