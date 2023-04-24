package com.example.weather.weather.usecases.weatherlocation

import com.example.weather.usecases.weatherlocation.LocationDataSource
import com.example.weather.usecases.weatherlocation.LocationService
import com.example.weather.usecases.weatherlocation.WeatherByLocationGetter
import com.example.weather.usecases.weatherlocation.WeatherByLocationGetterImpl
import com.example.weather.weather.usecases.common.RxImmediateSchedulerRule
import com.example.weather.weather.usecases.common.weatherActualModel
import com.example.weather.weather.usecases.common.weatherLocation
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherResponseWeatherByLocationGetterTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var locationRequest: LocationDataSource

    @Mock
    lateinit var locationService: LocationService

    lateinit var weatherResponseWeatherByLocationGetter: WeatherByLocationGetter

    @Before
    fun setUp() {
        weatherResponseWeatherByLocationGetter = WeatherByLocationGetterImpl(locationRequest, locationService)
    }

    @Test
    fun getWeatherTest() {
        Mockito.`when`(locationRequest.getWeatherLocation(weatherLocation)).thenReturn(
            Single.just(weatherActualModel))

        Mockito.`when`(locationService.getLocation()).thenReturn(
            Single.just(weatherLocation))

        weatherResponseWeatherByLocationGetter.getWeatherByLocation().test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValues(weatherActualModel)
            .assertComplete()
    }

    @Test
    fun getWeatherExceptionTest() {
        Mockito.`when`(locationRequest.getWeatherLocation(weatherLocation))
            .thenReturn(Single.error(Throwable()))

        Mockito.`when`(locationService.getLocation()).thenReturn(
            Single.just(weatherLocation))

        weatherResponseWeatherByLocationGetter.getWeatherByLocation().test()
            .assertValueCount(0)
            .assertNotComplete()
    }
}