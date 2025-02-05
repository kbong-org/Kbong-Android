package com.project.data.mapper

import com.project.data.model.BaseModel
import com.project.data.model.calender.HistoryDaysResponse
import com.project.domain.model.BaseModelContent
import com.project.domain.model.calender.HistoryDaysContent

fun BaseModel<List<HistoryDaysResponse>>.toDataModel(): BaseModelContent<List<HistoryDaysContent>> {
    return BaseModelContent(
        isSuccess = isSuccess,
        data = data?.toDataModel(),
        errorResponse = errorResponse.toDataModel(),
    )
}

private fun List<HistoryDaysResponse>.toDataModel(): List<HistoryDaysContent> {
    return map {
        HistoryDaysContent(
            day = it.day,
            hasGame = it.hasGame,
            emotion = it.emotion,
        )
    }
}