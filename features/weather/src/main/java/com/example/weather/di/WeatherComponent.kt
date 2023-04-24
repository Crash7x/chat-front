package com.example.weather.di

import com.example.weather.DailyWeatherFragment
import com.example.weather.common.WeatherFeatureApi
import com.example.weather.common.WeatherFeatureDependencies
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        WeatherModule::class
    ],
    dependencies = [
        WeatherFeatureDependencies::class
    ]
)
@Singleton
interface WeatherComponent : WeatherFeatureApi {

    fun inject(fragment: DailyWeatherFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: WeatherFeatureDependencies): WeatherComponent
    }
}