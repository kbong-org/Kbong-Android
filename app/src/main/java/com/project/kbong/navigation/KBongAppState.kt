package com.project.kbong.navigation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.project.presentation.home.navigateToHome
import com.project.presentation.my.navigateToMy


@Composable
fun rememberKBongAppState(
    navController: NavHostController = rememberNavController(),
): KBongAppState {
    return remember(navController) {
        KBongAppState(
            navController = navController,
        )
    }
}

class KBongAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    fun navigateToBottomNavigationDestination(bottomNavigationDestination: BottomNaviDestination) {
        val bottomNavigationOption =
            navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        when (bottomNavigationDestination) {
            BottomNaviDestination.CALENDER -> navController.navigateToHome(bottomNavigationOption)
            BottomNaviDestination.MY -> navController.navigateToMy(bottomNavigationOption)
        }
    }

    @Composable
    fun isBottomBarVisible(): Boolean {
        return when (currentDestination?.route) {

            else -> false
        }
    }

    val bottomBarDestination: List<BottomNaviDestination> = BottomNaviDestination.entries
}
