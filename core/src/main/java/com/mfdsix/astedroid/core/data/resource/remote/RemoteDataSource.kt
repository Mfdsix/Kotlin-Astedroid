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
                            it.data[0].nasaId,
                            it.data[0].title,
                            if(!it.links.isNullOrEmpty()) it.links[0].href else null,
                            it.data[0].description,
                            it.data[0].center,
                            it.data[0].dateCreated
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