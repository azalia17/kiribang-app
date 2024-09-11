package com.azalia.angkot.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "angkot_spot")
//@Entity
data class AngkotSpotsEntity (
    val lat: Double,
    val lng: Double,
    @PrimaryKey val id: Int? = null
)