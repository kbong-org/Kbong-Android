package com.project.domain.model.day


data class GameDayListContent(
    val myTeamDisplayName: String? = null,
    val gameDayContent: List<GameDayContent>
)

data class GameDayContent(
    val day: String,
    val hasGame: Boolean,
    val result: String?,
)
