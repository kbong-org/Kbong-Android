package com.project.data.model.calendar

import com.google.gson.annotations.SerializedName

data class HistoryDayListResponse(
    @SerializedName("days")
    val historyDayResponse: List<HistoryDayResponse>
)
data class HistoryDayResponse(
    @SerializedName("day")
    val day: String,
    @SerializedName("hasGame")
    val hasGame: Boolean,
    @SerializedName("emotion")
    val emotion: String?,
)
