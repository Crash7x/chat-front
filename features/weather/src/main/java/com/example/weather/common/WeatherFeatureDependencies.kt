package com.example.weather.common

import android.content.Context
import com.example.core.navigation.NavCommand
import com.example.moduleinjector.BaseFeatureDependencies
import com.example.weather.navigation.WeatherNavigationProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import retrofit2.Retrofit

interface WeatherFeatureDependencies : BaseFeatureDependencies {
    val retrofit: Retrofit
    val context: Context
    val navigation: WeatherNavigationProvider
    val router: MutableSharedFlow<NavCommand>
}