package com.project.data.datasource.calendar

import com.project.data.model.BaseModel
import com.project.data.model.calendar.HistoryDayListResponse
import com.project.data.model.day.DailyLogListResponse
import com.project.data.service.CalendarService
import javax.inject.Inject

class CalendarDataSourceImpl @Inject constructor(
    private val calendarService: CalendarService
) : CalendarDataSource {
    override suspend fun getCalendarHistoryGame(
        year: Int,
        month: Int
    ): BaseModel<HistoryDayListResponse> {
        return calendarService.getCalendarHistoryGame(year, month)
    }

    override suspend fun getDailyLog(year: Int, month: Int, day: Int): BaseModel<DailyLogListResponse> {
        return calendarService.getDailyLog(year, month, day)
    }
}