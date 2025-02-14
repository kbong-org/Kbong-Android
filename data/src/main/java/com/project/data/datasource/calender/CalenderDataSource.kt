package com.project.data.datasource.calender

import com.project.data.model.BaseModel
import com.project.data.model.calender.HistoryDayListResponse

interface CalenderDataSource {

    suspend fun getCalenderHistoryGame(year: Int, month: Int): BaseModel<HistoryDayListResponse>
}