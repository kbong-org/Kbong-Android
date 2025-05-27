package com.project.domain.model.user

data class MyPageDailyLog(
    val date: String = "",
    val logs: List<Logs> = listOf()
)

data class Logs(
    val id: Long = 0,
    val awayTeamDisplayName: String = "",
    val homeTeamDisplayName: String = "",
    val stadiumFullName: String = "",
    val type: String = "",
    val imagePath: String? = null,
    val imageCount: Int = 0
)
