package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Popular
import com.example.core.domain.repository.IPopularRepository
import com.example.core.domain.usecase.PopularUseCase
import kotlinx.coroutines.flow.Flow

class PopularInteractor(private val iPopularRepository: IPopularRepository) : PopularUseCase {
    override fun getAllPopular(): Flow<Resource<List<Popular>>> = iPopularRepository.getAllPopular()
}