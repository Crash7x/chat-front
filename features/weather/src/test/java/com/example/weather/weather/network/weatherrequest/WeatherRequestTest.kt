package com.example.weather.weather.network.weatherrequest

import com.example.weather.network.common.ApiWeatherService
import com.example.weather.network.weatherrequest.WeatherRequest
import com.example.weather.usecases.weatherloader.WeatherService
import com.example.weather.weather.usecases.common.RxImmediateSchedulerRule
import com.example.weather.weather.usecases.common.weatherActualModel
import com.example.weather.weather.usecases.common.weatherExpectedModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherRequestTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var apiWeatherService: ApiWeatherService

    lateinit var weatherRequest: WeatherService

    @Before
    fun setUp() {
        weatherRequest = WeatherRequest(apiWeatherService)
    }

    @Test
    fun getWeatherTest() {
        Mockito.`when`(apiWeatherService.getApi(weatherActualModel.cityName)).thenReturn(
            Single.just(weatherExpectedModel))

        weatherRequest.getWeather(weatherActualModel.cityName).test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValues(weatherActualModel)
            .assertComplete()
    }

    @Test
    fun getWeatherExceptionTest() {
        Mockito.`when`(apiWeatherService.getApi(weatherActualModel.cityName))
            .thenReturn(Single.error(Throwable()))

        weatherRequest.getWeather(weatherActualModel.cityName).test()
            .assertValueCount(0)
            .assertNotComplete()
    }
}