package com.mfdsix.astedroid.core.utils

import com.mfdsix.astedroid.core.data.resource.local.entity.AsteroidEntity
import com.mfdsix.astedroid.core.data.resource.remote.response.AsteroidResponse
import com.mfdsix.astedroid.core.domain.model.Asteroid

object DataMapper {
    fun mapResponsesToEntities(input: List<AsteroidResponse>): List<AsteroidEntity> {
        val entityList = ArrayList<AsteroidEntity>()
        input.map {
            val entity = AsteroidEntity(
                it.id,
                it.title,
                it.image,
                it.description,
                it.center,
                it.createdAt,
                false
            )
            entityList.add(entity)
        }
        return entityList
    }

    fun mapEntityToDomain(input: AsteroidEntity): Asteroid =
        Asteroid(
            input.id,
            input.title,
            input.image,
            input.description,
            input.center,
            input.createdAt,
            input.isFavorite
        )

    fun mapEntitiesToDomain(input: List<AsteroidEntity>): List<Asteroid> =
        input.map {
            Asteroid(
                it.id,
                it.title,
                it.image,
                it.description,
                it.center,
                it.createdAt,
                false
            )
        }

    fun mapDomainToEntity(input: Asteroid) = AsteroidEntity(
        input.id,
        input.title,
        input.image,
        input.description,
        input.center,
        input.createdAt,
        false
    )
}