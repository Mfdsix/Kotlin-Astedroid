package com.mfdsix.astedroid.core.data.resource.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroid")
data class AsteroidEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "image")
    var image: String?,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "center")
    var center: String,

    @ColumnInfo(name = "createdAt")
    var createdAt: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)