package com.example.cities

import androidx.lifecycle.ViewModel
import com.example.core.flow.MutableSingleEventFlow
import com.example.core.models.Icon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class CityViewModel : ViewModel() {
    private val _citiesList = MutableStateFlow<List<CityElement>>(emptyList())
    val citiesList get() = _citiesList.asStateFlow()

    private var _messageError = MutableSingleEventFlow<Int>()
    val messageError get() = _messageError.asSharedFlow()

    init {
        _citiesList.tryEmit(
            listOf(
                CityElement(
                    cityName = "Москва",
                    minimumTemperature = 25,
                    maximumTemperature = 25,
                    weatherIcon = Icon.CLOUD,
                ),
                CityElement(
                    cityName = "Санкт-Петербург",
                    minimumTemperature = 25,
                    maximumTemperature = 25,
                    weatherIcon = Icon.CLOUD,
                ),
                CityElement(
                    cityName = "Екатеринбург",
                    minimumTemperature = 13,
                    maximumTemperature = 10,
                    weatherIcon = Icon.CLOUD,
                ),
                CityElement(
                    cityName = "Минс",
                    minimumTemperature = 99,
                    maximumTemperature = 99,
                    weatherIcon = Icon.CLOUD,
                ),
                CityElement(
                    cityName = "Киев",
                    minimumTemperature = 25,
                    maximumTemperature = 25,
                    weatherIcon = Icon.CLOUD,
                ),
                CityElement(
                    cityName = "Тамбов",
                    minimumTemperature = 25,
                    maximumTemperature = 25,
                    weatherIcon = Icon.CLOUD,
                ),
                CityElement(
                    cityName = "Лондон",
                    minimumTemperature = 25,
                    maximumTemperature = 25,
                    weatherIcon = Icon.CLOUD,
                ),
                CityElement(
                    cityName = "Прага",
                    minimumTemperature = 25,
                    maximumTemperature = 25,
                    weatherIcon = Icon.CLOUD,
                ),
                CityElement(
                    cityName = "Бразилиа",
                    minimumTemperature = 25,
                    maximumTemperature = 25,
                    weatherIcon = Icon.CLOUD,
                ),
                CityElement(
                    cityName = "Санкт-Петербург",
                    minimumTemperature = 25,
                    maximumTemperature = 25,
                    weatherIcon = Icon.CLOUD,
                ),
                CityElement(
                    cityName = "Волгоград",
                    minimumTemperature = 25,
                    maximumTemperature = 25,
                    weatherIcon = Icon.CLOUD,
                ),
                CityElement(
                    cityName = "Омск",
                    minimumTemperature = 25,
                    maximumTemperature = 25,
                    weatherIcon = Icon.CLOUD,
                ),
                CityElement(
                    cityName = "Томск",
                    minimumTemperature = 25,
                    maximumTemperature = 25,
                    weatherIcon = Icon.CLOUD,
                )
            )
        )
    }

    fun returnBack() {

    }

    fun changeTheme() {

    }

    fun selectCity(cityElement: CityElement) {

    }

    fun addCity() {

    }

    fun selectCityFromMap() {

    }

    fun enterCityThroughDialogWindow() {

    }

    fun removeCity() {

    }
}