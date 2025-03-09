package com.project.data.model.calendar

import com.google.gson.annotations.SerializedName

data class GameDayListResponse(
    @SerializedName("myTeamDisplayName")
    val myTeamDisplayName: String,

    @SerializedName("days")
    val gameDayResponse: List<GameDayResponse>
)

data class GameDayResponse(
    @SerializedName("day")
    val day: String,
    @SerializedName("hasGame")
    val hasGame: Boolean,
    @SerializedName("result")
    val result: String?,
)
