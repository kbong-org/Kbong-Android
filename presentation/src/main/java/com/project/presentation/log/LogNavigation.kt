package com.project.presentation.log

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.data.model.log.DailyGameLogDto
import com.project.data.model.log.toDomain
import com.project.domain.model.day.DailyGameLog
import com.project.presentation.navigation.NavigationRoute
import java.time.LocalDate

fun NavController.navigateToSelectGame(date: LocalDate, navOptions: NavOptions? = null) {
    val formatted = date.toString() // yyyy-MM-dd
    navigate(NavigationRoute.SelectGameScreen.createRoute(formatted), navOptions)
}

fun NavGraphBuilder.selectGameNavigation(
    navController: NavController,
    onGameSelected: (gameId: Long) -> Unit = {}
) {
    composable(
        route = NavigationRoute.SelectGameScreen.route,
        arguments = listOf(
            navArgument("date") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val dateStr = backStackEntry.arguments?.getString("date")
        val selectedDate = dateStr?.let { LocalDate.parse(it) } ?: LocalDate.now()

        SelectGameScreen(
            navController = navController,
            selectedDate = selectedDate,
            onGameSelected = { selectedGame ->
                onGameSelected(selectedGame.id)
            }
        )
    }
}

fun NavController.navigateToGameLogWrite(gameInfo: DailyGameLogDto, selectedDate: LocalDate) {
    currentBackStackEntry?.savedStateHandle?.set("gameInfo", gameInfo)
    currentBackStackEntry?.savedStateHandle?.set("selectedDate", selectedDate)
    navigate(NavigationRoute.GameLogWriteScreen.route)
}

fun NavGraphBuilder.gameLogWriteNavigation(navController: NavController) {
    composable(route = NavigationRoute.GameLogWriteScreen.route) {
        val gameInfoDto = navController.previousBackStackEntry
            ?.savedStateHandle
            ?.get<DailyGameLogDto>("gameInfo")
        val selectedDate = navController.previousBackStackEntry
            ?.savedStateHandle
            ?.get<LocalDate>("selectedDate")

        if (gameInfoDto != null && selectedDate != null) {
            GameLogWriteRoute(
                navController = navController,
                gameInfo = gameInfoDto.toDomain(),
                selectedDate = selectedDate
            )
        }
    }
}