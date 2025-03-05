package com.project.domain.usecase

import com.project.domain.model.BaseModelContent
import com.project.domain.model.day.DailyLogList
import com.project.domain.repository.calendar.CalendarRepository
import javax.inject.Inject

class GetDailyLogUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository
) {

    suspend operator fun invoke(
        year: Int,
        month: Int,
        day: Int
    ): BaseModelContent<DailyLogList> {
        return calendarRepository.getDailyLog(year, month, day)
    }
}