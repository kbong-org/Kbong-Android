package com.project.presentation.navigation

import android.net.Uri

sealed class NavigationRoute(val route: String) {
    data object KaKaoLoginScreen : NavigationRoute("kakaoLogin")
    data object SignUpScreen : NavigationRoute("signup")
    data object CalendarScreen : NavigationRoute("calendar")
    data object MyScreen : NavigationRoute("my")
    data object SettingScreen : NavigationRoute("my/setting")
    data object ProfileEditScreen : NavigationRoute("my/profile")
    data object SelectGameScreen {
        const val route = "selectGame?date={date}&team={team}"
        fun createRoute(date: String, team: String) = "selectGame?date=$date&team=${Uri.encode(team)}"
    }
    data object GameLogWriteScreen : NavigationRoute("gameLogWrite")
    data object LogDetailScreen {
        const val route = "logDetail/{logId}?team={team}"
        fun createRoute(logId: Long, team: String): String = "logDetail/$logId?team=${Uri.encode(team)}"
    }
}