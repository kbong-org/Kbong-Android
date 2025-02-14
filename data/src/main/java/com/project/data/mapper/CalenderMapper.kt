package com.project.data.mapper

import com.project.data.model.BaseModel
import com.project.data.model.calender.HistoryDayListResponse
import com.project.data.model.calender.HistoryDayResponse
import com.project.domain.model.BaseModelContent
import com.project.domain.model.calender.HistoryDayContent
import com.project.domain.model.calender.HistoryDayListContent

fun BaseModel<HistoryDayListResponse>.toDataModel(): BaseModelContent<HistoryDayListContent> {
    return BaseModelContent(
        isSuccess = isSuccess,
        data = data?.toDataModel(),
        errorResponse = errorResponse.toDataModel(),
    )
}

private fun HistoryDayListResponse.toDataModel(): HistoryDayListContent {
    return HistoryDayListContent(
        historyDayListContent = this.historyDayResponse.map { it.toDataModel() }
    )
}

private fun HistoryDayResponse.toDataModel(): HistoryDayContent {
    return HistoryDayContent(
        day = this.day,
        hasGame = this.hasGame,
        emotion = this.emotion,
    )
}