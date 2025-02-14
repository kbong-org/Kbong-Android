package com.project.data.datasource.calender

import com.project.data.model.BaseModel
import com.project.data.model.calender.HistoryDayListResponse
import com.project.data.service.CalenderService
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
}