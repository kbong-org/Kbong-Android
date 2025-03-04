package com.project.presentation.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.project.presentation.navigation.NavigationRoute

fun NavController.navigateToKakaoLogin(navOptions: NavOptions? = null) =
    navigate(NavigationRoute.KaKaoLoginScreen.route, navOptions)

fun NavGraphBuilder.kakaoLoginNavigation() {
    composable(
        route = NavigationRoute.KaKaoLoginScreen.route,
    ) {
        KakaoLoginScreen()
    }
}