package com.azalia.angkot.domain.model

data class Stops(
    val name: String,
    val id: String,
    val openHours: String,
    val listAngkotId: List<String>,
    val cityId: Int,
)
