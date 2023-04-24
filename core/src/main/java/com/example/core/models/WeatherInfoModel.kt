package com.example.core.models

import com.example.core.R
import com.google.gson.annotations.SerializedName

enum class Icon(val image: Int) {
    @SerializedName("01d")
    SUN(R.drawable.sun),
    @SerializedName("02d")
    SUNS_CLOUD(R.drawable.suns_cloud),
    @SerializedName("03d")
    CLOUD(R.drawable.cloud),
    @SerializedName("04d")
    CLOUDS(R.drawable.cloud),
    @SerializedName("09d")
    RAIN(R.drawable.rain),
    @SerializedName("10d")
    RAINS(R.drawable.rain),
    @SerializedName("11d")
    THUNDER(R.drawable.thunder),
    @SerializedName("13d")
    SNOW(R.drawable.thunder),
    @SerializedName("50d")
    MIST(R.drawable.thunder),
    @SerializedName("01n")
    SUN_NIGHT(R.drawable.sun),
    @SerializedName("02n")
    SUNS_CLOUD_NIGHT(R.drawable.suns_cloud),
    @SerializedName("03n")
    CLOUD_NIGHT(R.drawable.cloud),
    @SerializedName("04n")
    CLOUDS_NIGHT(R.drawable.cloud),
    @SerializedName("09n")
    RAIN_NIGHT(R.drawable.rain),
    @SerializedName("10n")
    RAINS_NIGHT(R.drawable.rain),
    @SerializedName("11n")
    THUNDER_NIGHT(R.drawable.thunder),
    @SerializedName("13n")
    SNOW_NIGHT(R.drawable.thunder),
    @SerializedName("50n")
    MIST_NIGHT(R.drawable.thunder)
}

data class WeatherInfoModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: Icon
)
