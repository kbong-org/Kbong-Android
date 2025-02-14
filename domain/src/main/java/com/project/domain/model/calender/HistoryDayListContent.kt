package com.project.domain.model.calender


data class HistoryDayListContent(
    val historyDayListContent: List<HistoryDayContent>
)

data class HistoryDayContent(
    val day: String = "",
    val hasGame: Boolean = false,
    val emotion: String? = null,
)
