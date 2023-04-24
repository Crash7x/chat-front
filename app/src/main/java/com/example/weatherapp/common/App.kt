package com.example.weatherapp.common

import android.app.Application
import android.content.Context
import com.example.cities.dimodule.CityComponentHolder
import com.example.cities.dimodule.CityFeatureDependencies
import com.example.core.navigation.NavCommand
import com.example.moduleinjector.BaseDependencyHolder
import com.example.moduleinjector.DependencyHolder1
import com.example.weather.common.WeatherComponentHolder
import com.example.weather.common.WeatherFeatureDependencies
import com.example.weather.navigation.WeatherNavigationProvider
import com.example.weatherapp.common.di.AppComponent
import com.example.weatherapp.common.di.DaggerAppComponent
import kotlinx.coroutines.flow.MutableSharedFlow
import retrofit2.Retrofit


class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent
            .factory()
            .create(this)

        super.onCreate()

        connectModules()
    }

    private fun connectModules() {
        WeatherComponentHolder.dependencyProvider = {
            DependencyHolder1(
                api1 = appComponent
            ) { holder, appComponent ->
                object : WeatherFeatureDependencies {
                    override val retrofit: Retrofit = appComponent.retrofit
                    override val context: Context = appComponent.context
                    override val navigation: WeatherNavigationProvider = appComponent.navigationRouter()
                    override val router: MutableSharedFlow<NavCommand> = appComponent.navigationCommand()
                    override val dependencyHolder: BaseDependencyHolder<WeatherFeatureDependencies> = holder
                }
            }.dependencies
        }

        CityComponentHolder.dependencyProvider = {
            DependencyHolder1(
                api1 = appComponent
            ) { holder, appComponent ->
                object : CityFeatureDependencies {
                    override val retrofit: Retrofit = appComponent.retrofit
                    override val context: Context = appComponent.context
                    override val dependencyHolder: BaseDependencyHolder<CityFeatureDependencies> = holder
                }
            }.dependencies
        }
    }
}