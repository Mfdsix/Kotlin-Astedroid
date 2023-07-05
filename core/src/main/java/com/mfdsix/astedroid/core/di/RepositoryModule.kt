package com.mfdsix.astedroid.core.di

import com.mfdsix.astedroid.core.data.AsteroidRepository
import com.mfdsix.astedroid.core.domain.repository.IAsteroidRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(asteroidRepository: AsteroidRepository): IAsteroidRepository

}