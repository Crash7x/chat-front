package com.example.weather.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.core.navigation.NavCommand
import com.example.weather.DailyWeatherViewModel
import com.example.weather.navigation.WeatherNavigationProvider
import com.example.weather.navigation.WeatherRouter
import com.example.weather.navigation.WeatherRouterImpl
import com.example.weather.network.common.ApiWeatherService
import com.example.weather.network.locationrequest.LocationDataSourceImpl
import com.example.weather.network.weatherrequest.WeatherRequest
import com.example.weather.usecases.weatherloader.WeatherLoader
import com.example.weather.usecases.weatherloader.WeatherLoaderImpl
import com.example.weather.usecases.weatherloader.WeatherService
import com.example.weather.usecases.weatherlocation.LocationDataSource
import com.example.weather.usecases.weatherlocation.LocationService
import com.example.weather.usecases.weatherlocation.WeatherByLocationGetter
import com.example.weather.usecases.weatherlocation.WeatherByLocationGetterImpl
import com.example.weatherapp.weather.location.LocationServiceImpl
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.flow.MutableSharedFlow
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class WeatherModule {
    @Provides
    @IntoMap
    @ViewModelKey(DailyWeatherViewModel::class)
    @Singleton
    fun getViewModel(
        loadData: WeatherLoader,
        locations: WeatherByLocationGetter,
        router: WeatherRouter
    ): ViewModel {
        return DailyWeatherViewModel(loadData, locations, router)
    }

    @Provides
    @Singleton
    fun provideLoadDataUseCase(repository: WeatherService): WeatherLoader =
        WeatherLoaderImpl(repository)

    @Provides
    @Singleton
    fun provideRepositoryUseCase(service: ApiWeatherService): WeatherService =
        WeatherRequest(service)

    @Provides
    @Singleton
    fun provideWeatherLocation(
        request: LocationDataSource,
        getLocation: LocationService
    ): WeatherByLocationGetter =
        WeatherByLocationGetterImpl(request, getLocation)

    @Provides
    @Singleton
    fun provideWeatherRequestLocation(service: ApiWeatherService): LocationDataSource =
        LocationDataSourceImpl(service)

    @Provides
    @Singleton
    fun provideGetWeatherByLocation(context: Context): LocationService =
        LocationServiceImpl(context)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiWeatherService =
        retrofit.create(ApiWeatherService::class.java)

    @Provides
    @Singleton
    fun provideWeatherRouter(
        navigationCommand: MutableSharedFlow<NavCommand>,
        navigationProvider: WeatherNavigationProvider
    ): WeatherRouter = WeatherRouterImpl(navigationCommand, navigationProvider)
}