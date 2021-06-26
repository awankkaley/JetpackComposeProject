package com.example.core.data

import com.example.core.data.remote.ApiResponse
import com.example.core.domain.repository.IPopularRepository
import com.example.core.data.remote.RemoteDataSource
import com.example.core.domain.model.Popular
import com.example.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PopularRepository(private val remoteDataSource: RemoteDataSource) : IPopularRepository {
    override fun getAllPopular(): Flow<Resource<List<Popular>>> {
        return flow {
            emit(Resource.Loading())
            val apiResponse = remoteDataSource.getPopular()
            apiResponse.collect {
                when (it) {
                    is ApiResponse.Success -> {
                        val data = DataMapper.mapResponseToDomain(it.data)
                        emit(Resource.Success(data))
                    }
                    is ApiResponse.Empty -> {
                        emit(Resource.Error<List<Popular>>("EMPTY"))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error<List<Popular>>(it.errorMessage))
                    }
                }
            }
        }
    }

}