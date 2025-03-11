package com.project.presentation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.project.presentation.navigation.NavigationRoute

fun NavController.navigateToHome(navOptions: NavOptions? = null) =
    navigate(NavigationRoute.CalendarScreen.route, navOptions)

fun NavGraphBuilder.homeNavigation() {
    composable(
        route = NavigationRoute.CalendarScreen.route,
    ) {
        HomeRoute()
    }
}