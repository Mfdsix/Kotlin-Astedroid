package com.mfdsix.astedroid.core.data

import com.mfdsix.astedroid.core.data.resource.local.LocalDataSource
import com.mfdsix.astedroid.core.data.resource.remote.RemoteDataSource
import com.mfdsix.astedroid.core.data.resource.remote.network.ApiResponse
import com.mfdsix.astedroid.core.data.resource.remote.response.AsteroidResponse
import com.mfdsix.astedroid.core.domain.model.Asteroid
import com.mfdsix.astedroid.core.domain.repository.IAsteroidRepository
import com.mfdsix.astedroid.core.utils.AppExecutors
import com.mfdsix.astedroid.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AsteroidRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IAsteroidRepository {

    override fun getAll(): Flow<Resource<List<Asteroid>>> =
        object : NetworkBoundResource<List<Asteroid>, List<AsteroidResponse>>() {
            override fun loadFromDB(): Flow<List<Asteroid>> {
                return localDataSource.getAllAsteroids().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Asteroid>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<AsteroidResponse>>> =
                remoteDataSource.getAllAsteroid()

            override suspend fun saveCallResult(data: List<AsteroidResponse>) {
                val asteroidList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertAsteroid(asteroidList)
            }
        }.asFlow()

    override fun getDetail(asteroidId: String): Flow<Asteroid?> {
        return localDataSource.getDetailAsteroid(asteroidId).map {
            if(it != null){
                DataMapper.mapEntityToDomain(it)
            }else{
                null
            }
        }
    }

    override fun getFavorite(): Flow<List<Asteroid>> {
        return localDataSource.getFavoriteAsteroid().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setIsFavorite(tourism: Asteroid, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
    }
}