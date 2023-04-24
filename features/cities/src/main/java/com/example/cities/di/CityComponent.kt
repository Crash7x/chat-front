package com.example.cities.di

import com.example.cities.CityFragment
import com.example.cities.dimodule.CityFeatureApi
import com.example.cities.dimodule.CityFeatureDependencies
import dagger.Component

@Component(
    modules = [
        CityModule::class
    ],
    dependencies = [
        CityFeatureDependencies::class
    ]
)
interface CityComponent : CityFeatureApi {
    fun inject(cityFragment: CityFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: CityFeatureDependencies): CityComponent
    }
}