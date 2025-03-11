package com.project.data.repositoryimpl.calendar

import com.project.data.datasource.calendar.CalendarDataSource
import com.project.data.mapper.toBaseDomain
import com.project.data.mapper.toDomain
import com.project.domain.model.BaseModelContent
import com.project.domain.model.calendar.HistoryDayListContent
import com.project.domain.model.day.DailyLogList
import com.project.domain.repository.calendar.CalendarRepository
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val calendarDataSource: CalendarDataSource
) : CalendarRepository {

    override suspend fun getCalendarHistoryGame(
        year: Int,
        month: Int
    ): BaseModelContent<HistoryDayListContent> {
        return calendarDataSource.getCalendarHistoryGame(
            year = year,
            month = month
        ).toBaseDomain { it.toDomain() }
    }

    override suspend fun getDailyLog(
        year: Int,
        month: Int,
        day: Int
    ): BaseModelContent<DailyLogList> {
        return calendarDataSource.getDailyLog(year = year, month = month, day = day).toBaseDomain {
            it.toDomain()
        }
    }
}