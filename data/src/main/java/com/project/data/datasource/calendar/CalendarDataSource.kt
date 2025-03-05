package com.project.data.datasource.calendar

import com.project.data.model.BaseModel
import com.project.data.model.calendar.HistoryDayListResponse
import com.project.data.model.day.DailyLogListResponse

interface CalendarDataSource {

    suspend fun getCalendarHistoryGame(year: Int, month: Int): BaseModel<HistoryDayListResponse>

    suspend fun getDailyLog(year: Int, month: Int, day: Int): BaseModel<DailyLogListResponse>
}