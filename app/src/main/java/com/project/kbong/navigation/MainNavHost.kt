package com.project.kbong.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.project.data.LocalNavController
import com.project.presentation.auth.kakaoLoginNavigation
import com.project.presentation.home.homeNavigation
import com.project.presentation.my.myNavigation
import com.project.presentation.setting.settingNavigation
import com.project.presentation.signUp.signUpNavigation

@Composable
fun MainNavHost(
    appState: KBongAppState,
    modifier: Modifier = Modifier,
    startDestination: String,
) {
    CompositionLocalProvider(LocalNavController provides appState.navController) {
        NavHost(
            modifier = modifier,
            navController = appState.navController,
            startDestination = startDestination,
        ) {
            kakaoLoginNavigation()
            signUpNavigation(navController = appState.navController)
            homeNavigation()
            myNavigation()
            settingNavigation(navController = appState.navController)
        }
    }
}