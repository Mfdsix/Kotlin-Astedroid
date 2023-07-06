package com.mfdsix.astedroid.core.domain.repository

import com.mfdsix.astedroid.core.data.Resource
import com.mfdsix.astedroid.core.domain.model.Asteroid
import kotlinx.coroutines.flow.Flow

interface IAsteroidRepository {

    fun getAll(): Flow<Resource<List<Asteroid>>>

    fun getDetail(asteroidId: String): Flow<Asteroid?>

    fun getFavorite(): Flow<List<Asteroid>>

    fun setIsFavorite(tourism: Asteroid, state: Boolean)

}