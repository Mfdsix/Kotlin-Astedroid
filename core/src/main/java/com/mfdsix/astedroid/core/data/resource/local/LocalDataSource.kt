package com.mfdsix.astedroid.core.data.resource.local

import com.mfdsix.astedroid.core.data.resource.local.entity.AsteroidEntity
import com.mfdsix.astedroid.core.data.resource.local.room.AsteroidDao
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class LocalDataSource @Inject constructor(private val asteroidDao: AsteroidDao) {

    fun getAllAsteroids(): Flow<List<AsteroidEntity>> = asteroidDao.getAll()

    fun getDetailAsteroid(asteroidId: String): Flow<AsteroidEntity?> = asteroidDao.getDetail(asteroidId)

    fun getFavoriteAsteroid(): Flow<List<AsteroidEntity>> = asteroidDao.getFavorite()

    suspend fun insertAsteroid(asteroidList: List<AsteroidEntity>) = asteroidDao.insert(asteroidList)

    fun setFavoriteTourism(asteroid: AsteroidEntity, newState: Boolean) {
        asteroid.isFavorite = newState
        asteroidDao.updateIsFavorite(asteroid)
    }
}