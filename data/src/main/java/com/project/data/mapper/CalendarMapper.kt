package com.project.data.mapper

import com.project.data.model.calendar.HistoryDayListResponse
import com.project.data.model.calendar.HistoryDayResponse
import com.project.domain.model.calendar.HistoryDayContent
import com.project.domain.model.calendar.HistoryDayListContent


fun HistoryDayListResponse.toDomain(): HistoryDayListContent {
    return HistoryDayListContent(
        historyDayListContent = this.historyDayResponse.map { it.toDomain() }
    )
}

private fun HistoryDayResponse.toDomain(): HistoryDayContent {
    return HistoryDayContent(
        day = this.day,
        hasGame = this.hasGame,
        emotion = this.emotion,
    )
}