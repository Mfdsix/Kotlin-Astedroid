package com.mfdsix.astedroid.core.domain.usecase

import com.mfdsix.astedroid.core.domain.model.Asteroid
import com.mfdsix.astedroid.core.domain.repository.IAsteroidRepository
import javax.inject.Inject

class AsteroidInteractor @Inject constructor(private val asteroidRepository: IAsteroidRepository): AsteroidUseCase {

    override fun getAll() = asteroidRepository.getAll()

    override fun getFavorite() = asteroidRepository.getFavorite()

    override fun setIsFavorite(asteroid: Asteroid, state: Boolean) = asteroidRepository.setIsFavorite(asteroid, state)
}