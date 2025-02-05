package com.project.data.datasource.calender

import com.project.data.model.BaseModel
import com.project.data.model.calender.HistoryDaysResponse
import com.project.data.service.CalenderService
import javax.inject.Inject

class CalenderDataSourceImpl @Inject constructor(
    private val calenderService: CalenderService
) : CalenderDataSource {
    override suspend fun getCalenderHistoryGame(
        year: Int,
        month: Int
    ): BaseModel<List<HistoryDaysResponse>> {
        return calenderService.getCalenderHistoryGame(year, month)
    }
}