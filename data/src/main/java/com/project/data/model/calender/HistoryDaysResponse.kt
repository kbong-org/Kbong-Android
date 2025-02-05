package com.project.data.model.calender

import com.google.gson.annotations.SerializedName

data class HistoryDaysResponse(
    @SerializedName("day")
    val day: String,
    @SerializedName("hasGame")
    val hasGame: Boolean,
    @SerializedName("emotion")
    val emotion: String?,
)
