package com.example.weather.weather.network.locationrequest

import com.example.weather.network.common.ApiWeatherService
import com.example.weather.network.locationrequest.LocationDataSourceImpl
import com.example.weather.usecases.weatherlocation.LocationDataSource
import com.example.weather.weather.usecases.common.RxImmediateSchedulerRule
import com.example.weather.weather.usecases.common.weatherActualModel
import com.example.weather.weather.usecases.common.weatherExpectedModel
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
class WeatherByLocationGetterRequestTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var apiWeatherService: ApiWeatherService

    lateinit var weatherRequest: LocationDataSource

    @Before
    fun setUp() {
        weatherRequest = LocationDataSourceImpl(apiWeatherService)
    }

    @Test
    fun getWeatherLocationTest() {
        Mockito.`when`(apiWeatherService.getLocation(weatherLocation.latitude, weatherLocation.longitude)).thenReturn(
            Single.just(weatherExpectedModel))

        weatherRequest.getWeatherLocation(weatherLocation).test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValues(weatherActualModel)
            .assertComplete()
    }

    @Test
    fun getWeatherLocationExceptionTest() {
        Mockito.`when`(apiWeatherService.getLocation(weatherLocation.latitude, weatherLocation.longitude))
            .thenReturn(Single.error(Throwable()))

        weatherRequest.getWeatherLocation(weatherLocation).test()
            .assertValueCount(0)
            .assertNotComplete()
    }
}