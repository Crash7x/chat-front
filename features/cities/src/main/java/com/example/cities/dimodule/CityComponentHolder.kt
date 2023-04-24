package com.example.cities.dimodule

import com.example.cities.di.CityComponent
import com.example.cities.di.DaggerCityComponent
import com.example.moduleinjector.ComponentHolder
import com.example.moduleinjector.ComponentHolderDelegate

object CityComponentHolder : ComponentHolder<CityFeatureApi, CityFeatureDependencies> {
    private val componentHolderDelegate =
        ComponentHolderDelegate<CityFeatureApi, CityFeatureDependencies, CityComponent> { dependencies ->
            DaggerCityComponent
                .factory()
                .create(dependencies)
        }

    override var dependencyProvider by componentHolderDelegate::dependencyProvider

    override fun get(): CityFeatureApi =
        componentHolderDelegate.get()

    internal fun getComponentImpl() =
        componentHolderDelegate.getComponentImpl()
}