package com.project.data.repositoryimpl.calender

import com.project.data.datasource.calender.CalenderDataSource
import com.project.data.mapper.toDataModel
import com.project.domain.model.BaseModelContent
import com.project.domain.model.calender.HistoryDaysContent
import com.project.domain.repository.calender.CalenderRepository
import javax.inject.Inject

class CalenderRepositoryImpl @Inject constructor(
    private val calenderDataSource: CalenderDataSource
): CalenderRepository {

    override suspend fun getCalenderHistoryGame(
        year: Int,
        month: Int
    ): BaseModelContent<List<HistoryDaysContent>> {
        return calenderDataSource.getCalenderHistoryGame(
            year = year,
            month = month
        ).toDataModel()
    }
}