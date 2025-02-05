package com.project.data.datasource.calender

import com.project.data.model.BaseModel
import com.project.data.model.calender.HistoryDaysResponse

interface CalenderDataSource {

    suspend fun getCalenderHistoryGame(year: Int, month: Int): BaseModel<List<HistoryDaysResponse>>
}