package com.project.data.mapper

import com.project.data.model.day.DailyLogListResponse
import com.project.data.model.day.DailyLogResponse
import com.project.domain.model.day.DailyLog
import com.project.domain.model.day.DailyLogList


fun DailyLogListResponse.toDomain(): DailyLogList {
    return DailyLogList(
        dailyLogList = this.dailyLogResponse.map { it.toDomain() }
    )
}

private fun DailyLogResponse.toDomain(): DailyLog {
    return DailyLog(
        id = id,
        awayTeamDisplayName = awayTeamDisplayName,
        homeTeamDisplayName = homeTeamDisplayName,
        stadiumFullName = stadiumFullName,
        emotion = emotion,
        type = type,
      //  imageList = imageList
    )
}