package com.project.presentation.logwrite

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.project.presentation.navigation.NavigationRoute

fun NavController.navigateToLogWriteTeamSelect(navOptions: NavOptions? = null) =
    navigate(NavigationRoute.LogWriteTeamSelectScreen.route)

fun NavGraphBuilder.logWriteNavigation() {
    composable(
        route = NavigationRoute.LogWriteTeamSelectScreen.route,
    ) {
        LogWriteTeamSelectRoute()
    }
}