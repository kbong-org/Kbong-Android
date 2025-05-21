package com.project.data.model.day

import com.google.gson.annotations.SerializedName

data class DailyLogListResponse(
    @SerializedName("dailyLogs")
    val dailyLogResponse: List<DailyLogResponse>
)

data class DailyLogResponse(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("awayTeamDisplayName")
    val awayTeamDisplayName: String = "",

    @SerializedName("homeTeamDisplayName")
    val homeTeamDisplayName: String = "",

    @SerializedName("stadiumFullName")
    val stadiumFullName: String = "",

    @SerializedName("emotion")
    val emotion: String = "",

    @SerializedName("type")
    val type: String = "",

    @SerializedName("imagePaths")
    val imageList: List<String> = listOf()
)
