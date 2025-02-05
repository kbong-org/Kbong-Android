package com.project.domain.repository.calender

import com.project.domain.model.BaseModelContent
import com.project.domain.model.calender.HistoryDaysContent

interface CalenderRepository {

    suspend fun getCalenderHistoryGame(year: Int, month: Int): BaseModelContent<List<HistoryDaysContent>>

}