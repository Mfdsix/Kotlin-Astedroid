package com.mfdsix.astedroid.core.data.resource.remote.network

import com.mfdsix.astedroid.core.data.resource.remote.response.SearchAsteroidResponse
import retrofit2.http.GET

interface ApiService {
    @GET("search?q=asteroid")
    suspend fun getList(): SearchAsteroidResponse
}