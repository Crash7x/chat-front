package com.example.weather.network.common.model

import com.google.gson.annotations.SerializedName

data class SysModel(
    @SerializedName("pod")
    val pod: String
)
