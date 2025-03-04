package com.project.domain.model.day

data class DailyLogList(
    val dailyLogList: List<DailyLog> = listOf()
)

data class DailyLog(
    val id: Long = 0,
    val awayTeamDisplayName: String = "",
    val homeTeamDisplayName: String = "",
    val stadiumFullName: String = "",
    val emotion: String = "",
    val type: String = "",
    val imageList: List<String> = listOf()
)
