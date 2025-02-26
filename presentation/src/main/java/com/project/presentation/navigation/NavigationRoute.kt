package com.project.presentation.navigation

sealed class NavigationRoute(val route: String) {
    data object CalenderScreen : NavigationRoute("calender")
    data object MyScreen : NavigationRoute("my")
}