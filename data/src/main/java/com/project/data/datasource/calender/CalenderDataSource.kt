package com.project.data.datasource.calender

import com.project.data.model.BaseModel
import com.project.data.model.calender.HistoryDayListResponse
import com.project.data.model.day.DailyLogListResponse

interface CalenderDataSource {

    suspend fun getCalenderHistoryGame(year: Int, month: Int): BaseModel<HistoryDayListResponse>

    suspend fun getDailyLog(year: Int, month: Int, day: Int): BaseModel<DailyLogListResponse>
}