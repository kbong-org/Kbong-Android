package com.project.data.model.user

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("myTeam")
    val myTeam: MyTeamResponse = MyTeamResponse(),

    @SerializedName("nickname")
    val nickname: String = "",

    @SerializedName("birthDate")
    val birthDate: String? = null,

    @SerializedName("gender")
    val gender: String? = null,

    @SerializedName("visitedGames")
    val visitedGames: VisitedGamesResponse = VisitedGamesResponse(),

    @SerializedName("dailyLogs")
    val dailyLog: List<MyPageDailyLogResponse> = listOf()
)
