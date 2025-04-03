package com.project.presentation.setting

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.project.presentation.my.MyViewModel
import com.project.presentation.navigation.NavigationRoute

fun NavController.navigateToSetting(navOptions: NavOptions? = null) =
    navigate(NavigationRoute.SettingScreen.route, navOptions)

fun NavGraphBuilder.settingNavigation(navController: NavHostController) {
    composable(
        route = NavigationRoute.SettingScreen.route,
    ) { navBackStack ->
        val parentEntry = remember(navBackStack) {
            navController.getBackStackEntry(NavigationRoute.MyScreen.route)
        }

        val viewModel = hiltViewModel<MyViewModel>(parentEntry)

        SettingRoute(
            viewModel = viewModel
        )
    }
}