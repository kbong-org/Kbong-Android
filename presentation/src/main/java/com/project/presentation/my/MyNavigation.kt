package com.project.presentation.my

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.project.presentation.navigation.NavigationRoute

fun NavController.navigateToMy(navOptions: NavOptions? = null) =
    navigate(NavigationRoute.MyScreen.route, navOptions)

fun NavGraphBuilder.myNavigation() {
    composable(
        route = NavigationRoute.MyScreen.route,
    ) {
        MyRoute()
    }
}