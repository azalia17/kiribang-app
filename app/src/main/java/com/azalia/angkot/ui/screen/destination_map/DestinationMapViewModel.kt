package com.azalia.angkot.ui.screen.destination_map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azalia.angkot.data.AngkotSpotsDao
import com.azalia.angkot.data.toAngkotSpot
import com.azalia.angkot.data.toAngkotSpotEntity
import com.azalia.angkot.domain.model.AngkotSpot
import com.azalia.angkot.domain.repository.AngkotRepository
import com.azalia.angkot.ui.common.MapState
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class DestinationMapViewModel(private val repository: AngkotRepository) : ViewModel() {
    var state by mutableStateOf(MapState())
    private val _selectedLocation = mutableStateOf<LatLng?>(null)
    val selectedLocation get() = _selectedLocation

    // Function to set the selected location
    fun setSelectedLocation(location: LatLng) {
        _selectedLocation.value = location
    }

//    init {
//        viewModelScope.launch {
//
//            repository.getSpots().collectLatest { spots ->
//                state = state.copy(
//                    angkotSpots = spots
//                )
//            }
//        }
//    }

//    suspend fun insertSpots(spot: AngkotSpot) {
//        dao.insertAngkotSpot(spot.toAngkotSpotEntity())
//    }
//
//    suspend fun deleteSpots(spot: AngkotSpot) {
//        dao.deleteAngkotSpot(spot = spot.toAngkotSpotEntity())
//    }
//
//    fun getSpots(): Flow<List<AngkotSpot>> {
//        return dao.getAngkotSpot().map { spots ->
//            spots.map { it.toAngkotSpot() }
//        }
//    }

//    fun onEvent(event: DestinationMapEvent) {
//        when(event) {
//            is DestinationMapEvent.onMapLongClick -> {
//                viewModelScope.launch {
//                    repository.insertSpots(AngkotSpot(event.latlng.latitude, event.latlng.longitude))
//                }
//            }
//            is DestinationMapEvent.onInfoWindowLongClick -> {
//                viewModelScope.launch {
//                    repository.deleteSpots(event.spot)
//                }
//            }
//            else -> {}
//        }
//    }


}