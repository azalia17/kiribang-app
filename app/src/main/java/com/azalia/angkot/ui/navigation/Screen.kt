package com.azalia.angkot.ui.navigation

sealed class Screen (val route: String) {
    object Home : Screen("home")
    object List : Screen("list")
    object Map : Screen("map")
    object DetailAngkot : Screen("list/{id}") {
        fun createRoute(id: Int) = "list/$id"
    }
}