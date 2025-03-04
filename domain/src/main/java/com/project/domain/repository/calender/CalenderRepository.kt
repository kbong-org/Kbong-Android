package com.project.domain.repository.calender

import com.project.domain.model.BaseModelContent
import com.project.domain.model.calender.HistoryDayListContent
import com.project.domain.model.day.DailyLogList

interface CalenderRepository {

    suspend fun getCalenderHistoryGame(year: Int, month: Int): BaseModelContent<HistoryDayListContent>

    suspend fun getDailyLog(year: Int, month: Int, day: Int): BaseModelContent<DailyLogList>

}