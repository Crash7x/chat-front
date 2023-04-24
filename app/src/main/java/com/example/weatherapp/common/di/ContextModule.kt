package com.example.weatherapp.common.di

import android.content.Context
import com.example.weatherapp.common.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule {

    @Provides
    @Singleton
    fun provideContext(app: App): Context = app.applicationContext

}