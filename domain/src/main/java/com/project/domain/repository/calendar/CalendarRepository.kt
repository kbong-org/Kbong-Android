package com.project.domain.repository.calendar

import com.project.domain.model.BaseModelContent
import com.project.domain.model.calendar.HistoryDayListContent
import com.project.domain.model.day.DailyLogList
import com.project.domain.model.day.GameDayListContent

interface CalendarRepository {

    suspend fun getCalendarHistoryGame(year: Int, month: Int): BaseModelContent<HistoryDayListContent>

    suspend fun getDailyLog(year: Int, month: Int, day: Int): BaseModelContent<DailyLogList>

    suspend fun getGameList(year: Int, month: Int): BaseModelContent<GameDayListContent>

}