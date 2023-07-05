package com.mfdsix.astedroid.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Asteroid(
    val id: String,
    val title: String,
    val image: String,
    val description: String,
    val center: String,
    val createdAt: String,
    val isFavorite: Boolean
) : Parcelable