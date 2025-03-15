package com.project.data.mapper

import com.project.data.model.day.DailyGameLogListResponse
import com.project.data.model.day.DailyGameLogResponse
import com.project.domain.model.day.DailyGameLog
import com.project.domain.model.day.DailyGameLogList


fun DailyGameLogListResponse.toDomain(): DailyGameLogList {
    return DailyGameLogList(
        dailyGameLogList = this.dailyGameLogListResponse.map { it.toDomain() }
    )
}

private fun DailyGameLogResponse.toDomain(): DailyGameLog {
    return with(this) {
        DailyGameLog(
            id = id,
            startTimeStr = startTimeStr,
            status = status,
            awayTeamDisplayName = awayTeamDisplayName,
            homeTeamDisplayName = homeTeamDisplayName,
            stadiumDisplayName = stadiumDisplayName,
            awayTeamScore = awayTeamScore,
            homeTeamScore = homeTeamScore,
            myTeamStatus = myTeamStatus
        )
    }
}