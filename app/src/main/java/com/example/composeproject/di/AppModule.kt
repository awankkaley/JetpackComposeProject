package com.example.composeproject

import com.example.core.domain.usecase.PopularInteractor
import com.example.core.domain.usecase.PopularUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<PopularUseCase> { PopularInteractor(get()) }
}

@InternalCoroutinesApi
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}