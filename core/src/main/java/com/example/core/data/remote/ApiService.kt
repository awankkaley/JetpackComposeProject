package com.example.core.data.remote

import com.example.core.util.Util
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Util.popular)
    suspend fun getPopular(
        @Query("api_key") string: String
    ): PopularListResponse
}