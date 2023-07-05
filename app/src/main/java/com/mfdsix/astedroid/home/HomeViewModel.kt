package com.mfdsix.astedroid.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfdsix.astedroid.core.domain.usecase.AsteroidUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(asteroidUseCase: AsteroidUseCase) : ViewModel() {
    val asteroid = asteroidUseCase.getAll().asLiveData()
}