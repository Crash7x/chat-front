package com.example.weather.weather

import app.cash.turbine.testIn
import com.example.weather.DailyWeatherViewModel
import com.example.weather.usecases.common.DailyWeather
import com.example.weather.usecases.common.TodayWeather
import com.example.weather.usecases.weatherloader.WeatherLoader
import com.example.weather.usecases.weatherlocation.WeatherByLocationGetter
import com.example.weather.weather.usecases.common.messageError
import com.example.weather.weather.usecases.common.weatherActualModel
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DailyWeatherViewModelTest {
    @Mock
    lateinit var weatherLoader: WeatherLoader

    @Mock
    lateinit var locations: WeatherByLocationGetter

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDisplayDataWeather() = runTest {
        Mockito.`when`(weatherLoader.getWeather(weatherActualModel.cityName))
            .thenReturn(Single.just(weatherActualModel))

        val viewModel = DailyWeatherViewModel(weatherLoader, locations)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.displayDataWeather(weatherActualModel.cityName)

        assertEquals(emptyList<TodayWeather>(), testHeaderWeather.awaitItem())
        assertEquals(weatherActualModel.headerWeather, testHeaderWeather.awaitItem())
        testHeaderWeather.cancel()

        assertEquals(emptyList<DailyWeather>(), testDailyWeather.awaitItem())
        assertEquals(weatherActualModel.dailyWeather, testDailyWeather.awaitItem())
        testDailyWeather.cancel()

        assertEquals("", testCityName.awaitItem())
        assertEquals(weatherActualModel.cityName, testCityName.awaitItem())
        testCityName.cancel()

        testMessage.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDisplayDataWeatherWithException() = runTest {
        Mockito.`when`(weatherLoader.getWeather(weatherActualModel.cityName))
            .thenReturn(Single.error(Throwable()))

        val viewModel = DailyWeatherViewModel(weatherLoader, locations)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.displayDataWeather(weatherActualModel.cityName)

        assertEquals(messageError, testMessage.awaitItem())
        testMessage.cancel()

        assertEquals(emptyList<TodayWeather>(), testHeaderWeather.awaitItem())
        testHeaderWeather.cancel()

        assertEquals(emptyList<DailyWeather>(), testDailyWeather.awaitItem())
        testDailyWeather.cancel()

        assertEquals("", testCityName.awaitItem())
        testCityName.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getWeatherDataLocationTest() = runTest {
        Mockito.`when`(locations.getWeatherByLocation())
            .thenReturn(Single.just(weatherActualModel))

        val viewModel = DailyWeatherViewModel(weatherLoader, locations)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.getWeatherDataLocation()

        assertEquals(emptyList<TodayWeather>(), testHeaderWeather.awaitItem())
        assertEquals(weatherActualModel.headerWeather, testHeaderWeather.awaitItem())
        testHeaderWeather.cancel()

        assertEquals(emptyList<DailyWeather>(), testDailyWeather.awaitItem())
        assertEquals(weatherActualModel.dailyWeather, testDailyWeather.awaitItem())
        testDailyWeather.cancel()

        assertEquals("", testCityName.awaitItem())
        assertEquals(weatherActualModel.cityName, testCityName.awaitItem())
        testCityName.cancel()

        testMessage.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getWeatherDataLocationWithExceptionTest() = runTest {
        Mockito.`when`(locations.getWeatherByLocation())
            .thenReturn(Single.error(Throwable()))

        val viewModel = DailyWeatherViewModel(weatherLoader, locations)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.getWeatherDataLocation()

        assertEquals(messageError, testMessage.awaitItem())
        testMessage.cancel()

        assertEquals(emptyList<TodayWeather>(), testHeaderWeather.awaitItem())
        testHeaderWeather.cancel()

        assertEquals(emptyList<DailyWeather>(), testDailyWeather.awaitItem())
        testDailyWeather.cancel()

        assertEquals("", testCityName.awaitItem())
        testCityName.cancel()
    }
}