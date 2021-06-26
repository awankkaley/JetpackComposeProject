package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Popular
import kotlinx.coroutines.flow.Flow

interface IPopularRepository {
    fun getAllPopular(): Flow<Resource<List<Popular>>>
}