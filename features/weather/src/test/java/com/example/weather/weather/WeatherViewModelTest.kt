package com.example.weather.weather

import app.cash.turbine.testIn
import com.example.weather.DailyWeatherViewModel
import com.example.weather.network.common.ApiWeatherService
import com.example.weather.network.locationrequest.LocationDataSourceImpl
import com.example.weather.network.weatherrequest.WeatherRequest
import com.example.weather.usecases.common.DailyWeather
import com.example.weather.usecases.common.TodayWeather
import com.example.weather.usecases.weatherloader.WeatherLoaderImpl
import com.example.weather.usecases.weatherlocation.LocationService
import com.example.weather.usecases.weatherlocation.WeatherByLocationGetterImpl
import com.example.weather.weather.usecases.common.*
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var apiWeatherService: ApiWeatherService

    @Mock
    lateinit var locations: LocationService

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDisplayDataWeather() = runTest {
        Mockito.`when`(apiWeatherService.getApi(weatherActualModel.cityName)).thenReturn(
            Single.just(weatherExpectedModel))

        val weatherRequest = WeatherRequest(apiWeatherService)
        val weatherRequestLocation = LocationDataSourceImpl(apiWeatherService)
        val weatherLoader = WeatherLoaderImpl(weatherRequest)
        val weatherResponseLocation = WeatherByLocationGetterImpl(weatherRequestLocation, locations)

        val viewModel = DailyWeatherViewModel(weatherLoader, weatherResponseLocation)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.displayDataWeather(weatherActualModel.cityName)

        Assertions.assertEquals(emptyList<TodayWeather>(), testHeaderWeather.awaitItem())
        Assertions.assertEquals(weatherActualModel.headerWeather, testHeaderWeather.awaitItem())
        testHeaderWeather.cancel()

        Assertions.assertEquals(emptyList<DailyWeather>(), testDailyWeather.awaitItem())
        Assertions.assertEquals(weatherActualModel.dailyWeather, testDailyWeather.awaitItem())
        testDailyWeather.cancel()

        Assertions.assertEquals("", testCityName.awaitItem())
        Assertions.assertEquals(weatherActualModel.cityName, testCityName.awaitItem())
        testCityName.cancel()

        testMessage.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDisplayDataWeatherWithException() = runTest {
        Mockito.`when`(apiWeatherService.getApi(weatherActualModel.cityName))
            .thenReturn(Single.error(Throwable()))

        val weatherRequest = WeatherRequest(apiWeatherService)
        val weatherRequestLocation = LocationDataSourceImpl(apiWeatherService)
        val weatherLoader = WeatherLoaderImpl(weatherRequest)
        val weatherResponseLocation = WeatherByLocationGetterImpl(weatherRequestLocation, locations)

        val viewModel = DailyWeatherViewModel(weatherLoader, weatherResponseLocation)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.displayDataWeather(weatherActualModel.cityName)

        Assertions.assertEquals(messageError, testMessage.awaitItem())
        testMessage.cancel()

        Assertions.assertEquals(emptyList<TodayWeather>(), testHeaderWeather.awaitItem())
        testHeaderWeather.cancel()

        Assertions.assertEquals(emptyList<DailyWeather>(), testDailyWeather.awaitItem())
        testDailyWeather.cancel()

        Assertions.assertEquals("", testCityName.awaitItem())
        testCityName.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getWeatherDataLocationTest() = runTest {
        Mockito.`when`(apiWeatherService.getLocation(weatherLocation.latitude, weatherLocation.longitude))
            .thenReturn(Single.just(weatherExpectedModel))

        Mockito.`when`(locations.getLocation()).thenReturn(
            Single.just(weatherLocation))

        val weatherRequest = WeatherRequest(apiWeatherService)
        val weatherRequestLocation = LocationDataSourceImpl(apiWeatherService)
        val weatherLoader = WeatherLoaderImpl(weatherRequest)
        val weatherResponseLocation = WeatherByLocationGetterImpl(weatherRequestLocation, locations)

        val viewModel = DailyWeatherViewModel(weatherLoader, weatherResponseLocation)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.getWeatherDataLocation()

        Assertions.assertEquals(emptyList<TodayWeather>(), testHeaderWeather.awaitItem())
        Assertions.assertEquals(weatherActualModel.headerWeather, testHeaderWeather.awaitItem())
        testHeaderWeather.cancel()

        Assertions.assertEquals(emptyList<DailyWeather>(), testDailyWeather.awaitItem())
        Assertions.assertEquals(weatherActualModel.dailyWeather, testDailyWeather.awaitItem())
        testDailyWeather.cancel()

        Assertions.assertEquals("", testCityName.awaitItem())
        Assertions.assertEquals(weatherActualModel.cityName, testCityName.awaitItem())
        testCityName.cancel()

        testMessage.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getWeatherDataLocationWithExceptionTest() = runTest {
        Mockito.`when`(apiWeatherService.getLocation(weatherLocation.latitude, weatherLocation.longitude))
            .thenReturn(Single.error(Throwable()))

        Mockito.`when`(locations.getLocation()).thenReturn(
            Single.just(weatherLocation))

        val weatherRequest = WeatherRequest(apiWeatherService)
        val weatherRequestLocation = LocationDataSourceImpl(apiWeatherService)
        val weatherLoader = WeatherLoaderImpl(weatherRequest)
        val weatherResponseLocation = WeatherByLocationGetterImpl(weatherRequestLocation, locations)

        val viewModel = DailyWeatherViewModel(weatherLoader, weatherResponseLocation)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.getWeatherDataLocation()

        Assertions.assertEquals(messageError, testMessage.awaitItem())
        testMessage.cancel()

        Assertions.assertEquals(emptyList<TodayWeather>(), testHeaderWeather.awaitItem())
        testHeaderWeather.cancel()

        Assertions.assertEquals(emptyList<DailyWeather>(), testDailyWeather.awaitItem())
        testDailyWeather.cancel()

        Assertions.assertEquals("", testCityName.awaitItem())
        testCityName.cancel()
    }
}
