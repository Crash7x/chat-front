package com.example.weatherapp.navigation

import com.example.core.navigation.NavCommand
import com.example.weather.navigation.WeatherNavigationProvider
import com.example.weatherapp.BuildConfig
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Singleton

@Module
class NavigationModule {
    @Provides
    @Singleton
    fun providesNavigationScreen(): WeatherNavigationProvider {
        return if (BuildConfig.ENABLING_SCREEN_CITY == true) {
            NavigationFromWeatherScreenToCityScreen()
        } else {
            NavigationFromWeatherScreenToDialogScreen()
        }
    }

    @Provides
    @Singleton
    fun navigationCommand(): MutableSharedFlow<NavCommand> {
        return MutableSharedFlow(
            replay = 0,
            extraBufferCapacity = 1,
            BufferOverflow.DROP_OLDEST
        )
    }
}