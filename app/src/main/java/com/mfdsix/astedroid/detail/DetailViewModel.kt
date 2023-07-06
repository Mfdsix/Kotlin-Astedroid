package com.mfdsix.astedroid.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfdsix.astedroid.core.domain.model.Asteroid
import com.mfdsix.astedroid.core.domain.usecase.AsteroidUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val asteroidUseCase: AsteroidUseCase) : ViewModel() {
    fun toggleFavorite(asteroid: Asteroid, newStatus:Boolean) =
        asteroidUseCase.setIsFavorite(asteroid, newStatus)

    fun getDetail(asteroidId: String) = asteroidUseCase.getDetail(asteroidId).asLiveData()
}