package com.example.core.data.remote

import android.util.Log
import com.example.core.util.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getPopular(): Flow<ApiResponse<List<PopularItem>>> = flow {
        try {
            val response = apiService.getPopular(Util.API_KEY)
            val dataArray = response.results
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.results))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)
}