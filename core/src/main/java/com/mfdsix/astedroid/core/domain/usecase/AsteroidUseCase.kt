package com.mfdsix.astedroid.core.domain.usecase

import com.mfdsix.astedroid.core.data.Resource
import com.mfdsix.astedroid.core.domain.model.Asteroid
import kotlinx.coroutines.flow.Flow

interface AsteroidUseCase {
    fun getAll(): Flow<Resource<List<Asteroid>>>

    fun getDetail(asteroidId: String): Flow<Asteroid?>
    fun getFavorite(): Flow<List<Asteroid>>
    fun setIsFavorite(asteroid: Asteroid, state: Boolean)
}