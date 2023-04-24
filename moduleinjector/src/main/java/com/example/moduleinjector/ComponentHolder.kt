package com.example.moduleinjector

interface BaseFeatureDependencies {
    val dependencyHolder: BaseDependencyHolder<out BaseFeatureDependencies>
}

interface BaseFeatureApi

interface ComponentHolder<A : BaseFeatureApi, D : BaseFeatureDependencies> {
    var dependencyProvider: (() -> D)?
    fun get(): A
}