package com.azalia.angkot.model

data class Angkot (
    val id: String,
    val code: String,
    val route: List<Route>,
    val price: String,
    val interval: String,
    val cityId: Int,
)

