package com.project.data.mapper

import com.project.data.model.calender.HistoryDayListResponse
import com.project.data.model.calender.HistoryDayResponse
import com.project.domain.model.calender.HistoryDayContent
import com.project.domain.model.calender.HistoryDayListContent


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