package com.azalia.angkot.domain.model

data class Angkot (
    val id: String,
//    val code: String,
//    val route: List<Route>,
    val price: String,
    val interval: Int,
    val cityId: Int,
    val image: Int,
)

