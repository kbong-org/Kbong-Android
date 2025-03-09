package com.project.domain.usecase.calendar

import com.project.domain.model.BaseModelContent
import com.project.domain.model.calendar.HistoryDayListContent
import com.project.domain.repository.calendar.CalendarRepository
import javax.inject.Inject

class GetCalendarHistoryGameUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository
) {

    suspend operator fun invoke(year: Int, month: Int): BaseModelContent<HistoryDayListContent> {
        return calendarRepository.getCalendarHistoryGame(
            year = year,
            month = month
        )
    }
}