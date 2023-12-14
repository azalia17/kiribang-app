package com.azalia.angkot.ui.screen.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.azalia.angkot.domain.repository.AngkotRepository
import com.azalia.angkot.domain.model.Angkot
import com.azalia.angkot.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListViewModel(private val repository: AngkotRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Angkot>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Angkot>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllAngkots() {
        viewModelScope.launch {
            repository.getAllAngkots()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {angkots ->
                    _uiState.value = UiState.Success(angkots)
                }
        }
    }
}