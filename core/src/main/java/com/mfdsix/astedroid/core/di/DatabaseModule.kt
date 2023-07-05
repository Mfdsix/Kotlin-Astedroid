package com.mfdsix.astedroid.core.di

import android.content.Context
import androidx.room.Room
import com.mfdsix.astedroid.core.data.resource.local.room.AsteroidDao
import com.mfdsix.astedroid.core.data.resource.local.room.AsteroidDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AsteroidDatabase = Room.databaseBuilder(
        context,
        AsteroidDatabase::class.java, "Asteroid.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: AsteroidDatabase): AsteroidDao = database.asteroidDao()
}