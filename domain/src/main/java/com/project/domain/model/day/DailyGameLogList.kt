package com.project.domain.model.day

data class DailyGameLogList(
    val dailyGameLogList: List<DailyGameLog> = listOf()
)

data class DailyGameLog(
    val id: Long = 0,
    val startTimeStr: String = "",
    val status: String = "",
    val awayTeamDisplayName: String = "",
    val homeTeamDisplayName: String = "",
    val stadiumDisplayName: String = "",
    val awayTeamScore: Int? = null,
    val homeTeamScore: Int? = null,
    val myTeamStatus: String = ""
)