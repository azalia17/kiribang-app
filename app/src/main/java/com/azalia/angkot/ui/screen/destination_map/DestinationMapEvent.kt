package com.azalia.angkot.ui.screen.destination_map

import com.azalia.angkot.domain.model.AngkotSpot
import com.google.android.gms.maps.model.LatLng

sealed class DestinationMapEvent {
//    object
    data class onMapLongClick(val latlng: LatLng): DestinationMapEvent()
    data class onInfoWindowLongClick(val spot: AngkotSpot): DestinationMapEvent()
}