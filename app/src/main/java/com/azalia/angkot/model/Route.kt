package com.azalia.angkot.model

data class Route (
    val angkotId: String,
    val id: Int,
    val name: String,
    val estimated: Int,
    val route: List<String>,
)