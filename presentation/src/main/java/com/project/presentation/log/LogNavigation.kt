package com.project.presentation.log

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.data.model.log.DailyGameLogDto
import com.project.data.model.log.toDomain
import com.project.presentation.log.detail.LogDetailScreen
import com.project.presentation.log.select.SelectGameScreen
import com.project.presentation.log.write.GameLogWriteRoute
import com.project.presentation.navigation.NavigationRoute
import java.time.LocalDate

fun NavController.navigateToSelectGame(
    date: LocalDate,
    myTeamDisplayName: String,
    navOptions: NavOptions? = null
) {
    val dateStr = date.toString() // yyyy-MM-dd
    val teamEncoded = Uri.encode(myTeamDisplayName)
    navigate("selectGame?date=$dateStr&team=$teamEncoded", navOptions)
}

fun NavGraphBuilder.selectGameNavigation(
    navController: NavController,
    onGameSelected: (gameId: Long) -> Unit = {}
) {
    composable(
        route = NavigationRoute.SelectGameScreen.route,
        arguments = listOf(
            navArgument("date") { type = NavType.StringType },
            navArgument("team") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val dateStr = backStackEntry.arguments?.getString("date") ?: ""
        val team = backStackEntry.arguments?.getString("team") ?: ""

        val selectedDate = if (dateStr.isNotBlank()) LocalDate.parse(dateStr) else LocalDate.now()

        SelectGameScreen(
            navController = navController,
            selectedDate = selectedDate,
            myTeamDisplayName = team,
            onGameSelected = { selectedGame ->
                onGameSelected(selectedGame.id)
            }
        )
    }
}

fun NavController.navigateToGameLogWrite(gameInfo: DailyGameLogDto, selectedDate: LocalDate, myTeamDisplayName: String) {
    currentBackStackEntry?.savedStateHandle?.set("gameInfo", gameInfo)
    currentBackStackEntry?.savedStateHandle?.set("selectedDate", selectedDate)
    currentBackStackEntry?.savedStateHandle?.set("myTeamDisplayName", myTeamDisplayName)
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
        val myTeamDisplayName = navController.previousBackStackEntry
            ?.savedStateHandle
            ?.get<String>("myTeamDisplayName")

        if (gameInfoDto != null && selectedDate != null && myTeamDisplayName != null) {
            GameLogWriteRoute(
                navController = navController,
                gameInfo = gameInfoDto.toDomain(),
                selectedDate = selectedDate,
                myTeamDisplayName = myTeamDisplayName
            )
        }
    }
}

fun NavController.navigateToLogDetail(logId: Long, myTeam: String, navOptions: NavOptions? = null) {
    val route = NavigationRoute.LogDetailScreen.createRoute(logId, myTeam)
    navigate(route, navOptions)
}

fun NavGraphBuilder.logDetailNavigation(navController: NavController) {
    composable(
        route = NavigationRoute.LogDetailScreen.route,
        arguments = listOf(
            navArgument("logId") { type = NavType.LongType },
            navArgument("team") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val logId = backStackEntry.arguments?.getLong("logId") ?: return@composable
        val myTeam = backStackEntry.arguments?.getString("team") ?: ""

        LogDetailScreen(
            logId = logId,
            myTeamDisplayName = myTeam,
            onBack = { navController.popBackStack() }
        )
    }
}