package com.project.data.model.user

import com.google.gson.annotations.SerializedName

data class VisitedGamesResponse(
    @SerializedName("total")
    val total: Int = 0,

    @SerializedName("myTeamWins")
    val myTeamWins: Int = 0,

    @SerializedName("myTeamLosses")
    val myTeamLosses: Int = 0,

    @SerializedName("myTeamDraws")
    val myTeamDraws: Int = 0,

    @SerializedName("myTeamRate")
    val myTeamRate: Int = 0
)
