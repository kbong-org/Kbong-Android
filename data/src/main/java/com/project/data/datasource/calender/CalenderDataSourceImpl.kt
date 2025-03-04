package com.project.data.datasource.calender

import com.project.data.model.BaseModel
import com.project.data.model.calender.HistoryDayListResponse
import com.project.data.model.day.DailyLogListResponse
import com.project.data.service.CalenderService
import com.project.domain.model.day.DailyLogList
import javax.inject.Inject

class CalenderDataSourceImpl @Inject constructor(
    private val calenderService: CalenderService
) : CalenderDataSource {
    override suspend fun getCalenderHistoryGame(
        year: Int,
        month: Int
    ): BaseModel<HistoryDayListResponse> {
        return calenderService.getCalenderHistoryGame(year, month)
    }

    override suspend fun getDailyLog(year: Int, month: Int, day: Int): BaseModel<DailyLogListResponse> {
        return calenderService.getDailyLog(year, month, day)
    }
}