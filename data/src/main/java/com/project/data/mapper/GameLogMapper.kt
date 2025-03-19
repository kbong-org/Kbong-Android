package com.project.data.mapper

import com.project.data.model.calendar.GameDayListResponse
import com.project.data.model.calendar.GameDayResponse
import com.project.domain.model.calendar.GameDayContent
import com.project.domain.model.calendar.GameDayListContent


fun GameDayListResponse.toDomain(): GameDayListContent {
    return GameDayListContent(
        myTeamDisplayName = myTeamDisplayName,
        gameDayContent = gameDayResponse.map { it.toDomain() }
    )
}

private fun GameDayResponse.toDomain(): GameDayContent {
    return GameDayContent(
        day = day,
        hasGame = hasGame,
        result = result
    )
}