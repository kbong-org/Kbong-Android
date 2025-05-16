package com.project.presentation.navigation

sealed class NavigationRoute(val route: String) {
    data object KaKaoLoginScreen : NavigationRoute("kakaoLogin")
    data object SignUpScreen : NavigationRoute("signup")
    data object CalendarScreen : NavigationRoute("calendar")
    data object MyScreen : NavigationRoute("my")
    data object SelectGameScreen : NavigationRoute("selectGame?date={date}") {
        fun createRoute(date: String): String = "selectGame?date=$date"
    }
    data object GameLogWriteScreen : NavigationRoute("gameLogWrite")
}