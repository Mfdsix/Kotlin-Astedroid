package com.mfdsix.astedroid.core.data.resource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mfdsix.astedroid.core.data.resource.local.entity.AsteroidEntity

@Database(entities = [AsteroidEntity::class], version = 1, exportSchema = false)
abstract class AsteroidDatabase : RoomDatabase() {

    abstract fun asteroidDao(): AsteroidDao

}