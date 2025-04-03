package com.project.presentation.setting.edit

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.project.presentation.my.MyViewModel
import com.project.presentation.navigation.NavigationRoute

fun NavController.navigateToProfileEdit(navOptions: NavOptions? = null) =
    navigate(NavigationRoute.ProfileEditScreen.route, navOptions)

fun NavGraphBuilder.profileEditNavigation(navController: NavHostController) {
    composable(
        route = NavigationRoute.ProfileEditScreen.route,
    ) { navBackStack ->

        val parentEntry = remember(navBackStack) {
            navController.getBackStackEntry(NavigationRoute.MyScreen.route)
        }

        val viewModel = viewModel<MyViewModel>(parentEntry)

        ProfileEditRoute(
            viewModel = viewModel
        )
    }
}

