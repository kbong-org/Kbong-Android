package com.project.presentation.signUp

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.presentation.auth.AuthViewModel
import com.project.presentation.navigation.NavigationRoute

fun NavController.navigateToSignUp(idToken: String, navOptions: NavOptions? = null) =
    navigate("${NavigationRoute.SignUpScreen.route}/$idToken", navOptions)

fun NavGraphBuilder.signUpNavigation(navController: NavHostController) {
    composable(
        route = "${NavigationRoute.SignUpScreen.route}/{idToken}",
        arguments = listOf(
            navArgument("idToken") { type = NavType.StringType }
        )
    ) { navBackStack ->
        val parentEntry = remember(navBackStack) {
            navController.getBackStackEntry(NavigationRoute.KaKaoLoginScreen.route)
        }

        val viewModel = viewModel<AuthViewModel>(parentEntry)
        val idToken = navBackStack.arguments?.getString("idToken") ?: ""

        SignUpScreen(
            idToken = idToken,
            authViewModel = viewModel
        )
    }
}