package com.project.data.repositoryimpl.calender

import com.project.data.datasource.calender.CalenderDataSource
import com.project.data.mapper.toBaseDomain
import com.project.data.mapper.toDomain
import com.project.data.mapper.toDomain
import com.project.domain.model.BaseModelContent
import com.project.domain.model.calender.HistoryDayListContent
import com.project.domain.model.day.DailyLogList
import com.project.domain.repository.calender.CalenderRepository
import javax.inject.Inject

class CalenderRepositoryImpl @Inject constructor(
    private val calenderDataSource: CalenderDataSource
) : CalenderRepository {

    override suspend fun getCalenderHistoryGame(
        year: Int,
        month: Int
    ): BaseModelContent<HistoryDayListContent> {
        return calenderDataSource.getCalenderHistoryGame(
            year = year,
            month = month
        ).toBaseDomain { it.toDomain() }
    }

    override suspend fun getDailyLog(
        year: Int,
        month: Int,
        day: Int
    ): BaseModelContent<DailyLogList> {
        return calenderDataSource.getDailyLog(year = year, month = month, day = day).toBaseDomain {
            it.toDomain()
        }
    }
}