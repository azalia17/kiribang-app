package com.azalia.angkot.ui.common

import com.azalia.angkot.domain.model.AngkotSpot
import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(),
    val angkotSpots: List<AngkotSpot> = emptyList(),
    val isFalloutMap: Boolean = false
)
