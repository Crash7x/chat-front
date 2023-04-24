package com.example.weather.network.common.model

import com.google.gson.annotations.SerializedName

data class CloudsModel(
    @SerializedName("all")
    val all: Int
)
