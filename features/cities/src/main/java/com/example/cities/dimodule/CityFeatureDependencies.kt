package com.example.cities.dimodule

import android.content.Context
import com.example.moduleinjector.BaseFeatureDependencies
import retrofit2.Retrofit

interface CityFeatureDependencies : BaseFeatureDependencies {
    val retrofit: Retrofit
    val context: Context
}