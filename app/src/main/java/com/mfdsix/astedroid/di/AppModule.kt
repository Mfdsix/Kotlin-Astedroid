@file:Suppress("unused")

package com.mfdsix.astedroid.di

import com.mfdsix.astedroid.core.domain.usecase.AsteroidInteractor
import com.mfdsix.astedroid.core.domain.usecase.AsteroidUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideAsteroidUseCase(asteroidInteractor: AsteroidInteractor): AsteroidUseCase

}