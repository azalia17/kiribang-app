package com.azalia.angkot.ui.screen.detail_angkot

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azalia.angkot.domain.model.Angkot
import com.azalia.angkot.domain.repository.AngkotRepository
import com.azalia.angkot.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailAngkotViewModel(private val repository: AngkotRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Angkot>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Angkot>> get() = _uiState

    fun getAngkotById(angkotId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getAngkotById(angkotId))
        }
    }
}