package com.azalia.angkot.data

import com.azalia.angkot.domain.model.AngkotSpot

fun AngkotSpotsEntity.toAngkotSpot(): AngkotSpot {
    return AngkotSpot(
        lat = lat,
        lng = lng,
        id = id
    )
}

fun AngkotSpot.toAngkotSpotEntity(): AngkotSpotsEntity {
    return  AngkotSpotsEntity(
        lat = lat,
        lng = lng,
        id = id
    )
}