package com.project.domain.model.calendar


data class HistoryDayListContent(
    val historyDayListContent: List<HistoryDayContent>
)

data class HistoryDayContent(
    val day: String = "1",
    val hasGame: Boolean = false,
    val emotion: String? = null,
)
