package com.project.domain.model.day

data class DailyLog(
    val id: Long = 0,
    val awayTeamDisplayName: String = "",
    val homeTeamDisplayName: String = "",
    val stadiumFullName: String = "",
    val emotion: String = "",
    val type: String = "",
    val imageList: List<String> = listOf()
)
