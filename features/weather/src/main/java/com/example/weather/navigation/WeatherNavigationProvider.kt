package com.example.weather.navigation

import com.example.core.navigation.NavCommand

interface WeatherNavigationProvider {
    fun createCommandNavigationToScreenCity(): NavCommand
}