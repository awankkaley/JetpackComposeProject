package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Popular
import kotlinx.coroutines.flow.Flow

interface PopularUseCase {
    fun getAllPopular(): Flow<Resource<List<Popular>>>

}