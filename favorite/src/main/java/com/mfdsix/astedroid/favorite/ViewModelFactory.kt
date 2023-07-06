package com.mfdsix.astedroid.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mfdsix.astedroid.core.domain.usecase.AsteroidUseCase
import com.mfdsix.astedroid.favorite.favorite.FavoriteViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val asteroidUseCase: AsteroidUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(asteroidUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}