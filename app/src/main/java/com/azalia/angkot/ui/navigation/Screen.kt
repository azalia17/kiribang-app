package com.azalia.angkot.ui.navigation

sealed class Screen (val route: String) {
    object Home : Screen("home")
    object List : Screen("list")
    object Map : Screen("map")
}