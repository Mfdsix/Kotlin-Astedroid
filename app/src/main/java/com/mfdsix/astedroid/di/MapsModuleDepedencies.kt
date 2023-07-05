package com.mfdsix.astedroid.di

import com.mfdsix.astedroid.core.domain.usecase.AsteroidUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MapsModuleDependencies {

    fun asteroidUseCase(): AsteroidUseCase
}