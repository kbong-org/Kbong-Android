package com.project.data.model.day

import com.google.gson.annotations.SerializedName

data class DailyGameLogListResponse(
    @SerializedName("games")
    val dailyGameLogListResponse: List<DailyGameLogResponse> = listOf()
)

data class DailyGameLogResponse(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("startTimeStr")
    val startTimeStr: String = "",

    @SerializedName("status")
    val status: String = "",

    @SerializedName("awayTeamDisplayName")
    val awayTeamDisplayName: String = "",

    @SerializedName("homeTeamDisplayName")
    val homeTeamDisplayName: String = "",

    @SerializedName("stadiumDisplayName")
    val stadiumDisplayName: String = "",

    @SerializedName("awayTeamScore")
    val awayTeamScore: Int? = null,

    @SerializedName("homeTeamScore")
    val homeTeamScore: Int? = null,

    @SerializedName("myTeamStatus")
    val myTeamStatus: String = ""
)