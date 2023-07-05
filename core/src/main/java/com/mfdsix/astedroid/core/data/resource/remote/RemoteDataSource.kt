package com.mfdsix.astedroid.core.data.resource.remote

import com.mfdsix.astedroid.core.data.resource.remote.network.ApiResponse
import com.mfdsix.astedroid.core.data.resource.remote.network.ApiService
import com.mfdsix.astedroid.core.data.resource.remote.response.AsteroidResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllAsteroid(): Flow<ApiResponse<List<AsteroidResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.collection.items
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.collection.items.map {
                        AsteroidResponse(
                            it.data.nasaId,
                            it.data.title,
                            it.links[0].href,
                            it.data.description,
                            it.data.center,
                            it.data.dateCreated
                        )
                    }))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}