package com.azalia.angkot.ui.screen.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.azalia.angkot.ui.common.MapState

class MapViewModel() : ViewModel() {
    var state by mutableStateOf(MapState())
}