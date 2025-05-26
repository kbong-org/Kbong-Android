package com.project.data.model.user

import com.google.gson.annotations.SerializedName

data class MyPageDailyLogResponse(
    @SerializedName("date")
    val date: String = "",

    @SerializedName("logs")
    val logs: List<LogsResponse> = listOf()
)

data class LogsResponse(
    @SerializedName("logs")
    val id: Long = 0,

    @SerializedName("awayTeamDisplayName")
    val awayTeamDisplayName: String = "",

    @SerializedName("homeTeamDisplayName")
    val homeTeamDisplayName: String = "",

    @SerializedName("stadiumFullName")
    val stadiumFullName: String = "",

    @SerializedName("type")
    val type: String = "",

    @SerializedName("imagePath")
    val imagePath: String = "",

    @SerializedName("imageCount")
    val imageCount: Int = 0
)
