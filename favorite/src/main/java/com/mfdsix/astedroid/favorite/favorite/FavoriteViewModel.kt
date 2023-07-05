package com.mfdsix.astedroid.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfdsix.astedroid.core.domain.usecase.AsteroidUseCase

class FavoriteViewModel(asteroidUseCase: AsteroidUseCase) : ViewModel() {
    val asteroid = asteroidUseCase.getFavorite().asLiveData()
}