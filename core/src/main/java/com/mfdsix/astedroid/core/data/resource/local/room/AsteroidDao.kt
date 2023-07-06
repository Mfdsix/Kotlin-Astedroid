package com.mfdsix.astedroid.core.data.resource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mfdsix.astedroid.core.data.resource.local.entity.AsteroidEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM asteroid")
    fun getAll(): Flow<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroid where id = :asteroidId")
    fun getDetail(asteroidId: String): Flow<AsteroidEntity?>

    @Query("SELECT * FROM asteroid where isFavorite = 1")
    fun getFavorite(): Flow<List<AsteroidEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tourism: List<AsteroidEntity>)

    @Update
    fun updateIsFavorite(tourism: AsteroidEntity)
}