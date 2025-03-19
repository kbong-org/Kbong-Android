package com.project.domain.usecase.calendar

import com.project.domain.model.BaseModelContent
import com.project.domain.model.day.DailyGameLogList
import com.project.domain.repository.calendar.CalendarRepository
import javax.inject.Inject

class GetDailyGameLogUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository
) {

    suspend operator fun invoke(
        year: Int,
        month: Int,
        day: Int
    ): BaseModelContent<DailyGameLogList> {
        return calendarRepository.getDailyGameLog(year, month, day)
    }
}