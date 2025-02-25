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

    // todo 이미지 리스폰스 추가 되면 해놓기
    /*@SerializedName("stadiumFullName")
    val imageList: List<String> = listOf()*/
)
