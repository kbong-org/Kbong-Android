package com.project.kbong.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.project.data.LocalNavController
import com.project.presentation.auth.kakaoLoginNavigation
import com.project.presentation.home.homeNavigation
import com.project.presentation.log.gameLogWriteNavigation
import com.project.presentation.log.logDetailNavigation
import com.project.presentation.log.selectGameNavigation
import com.project.presentation.my.myNavigation
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
            homeNavigation(navController = appState.navController)
            selectGameNavigation(navController = appState.navController)
            myNavigation()
            gameLogWriteNavigation(navController = appState.navController)
            logDetailNavigation(navController = appState.navController)
        }
    }
}