package com.example.weather.common

import com.example.moduleinjector.ComponentHolder
import com.example.moduleinjector.ComponentHolderDelegate
import com.example.weather.di.DaggerWeatherComponent
import com.example.weather.di.WeatherComponent

object WeatherComponentHolder : ComponentHolder<WeatherFeatureApi, WeatherFeatureDependencies> {

    private val componentHolderDelegate =
        ComponentHolderDelegate<WeatherFeatureApi, WeatherFeatureDependencies, WeatherComponent> { dependencies ->
            DaggerWeatherComponent
                .factory()
                .create(dependencies)
        }

    override var dependencyProvider by componentHolderDelegate::dependencyProvider

    override fun get(): WeatherFeatureApi =
        componentHolderDelegate.get()

    internal fun getComponentImpl() =
        componentHolderDelegate.getComponentImpl()

}