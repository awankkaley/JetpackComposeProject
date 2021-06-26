package com.example.composeproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Popular
import com.example.core.domain.usecase.PopularUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class MainViewModel(private val popularUseCase: PopularUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<List<Popular>>(listOf())
    val uiState: StateFlow<List<Popular>> = _uiState

    init {
        viewModelScope.launch {
            popularUseCase.getAllPopular().map {
                it.data
            }.collect { result ->
                if (result != null) {
                    _uiState.value = result
                }
            }
        }
    }

}