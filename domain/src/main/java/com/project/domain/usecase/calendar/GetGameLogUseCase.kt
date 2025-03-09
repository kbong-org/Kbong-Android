package com.project.domain.usecase.calendar

import com.project.domain.model.BaseModelContent
import com.project.domain.model.day.GameDayListContent
import com.project.domain.repository.calendar.CalendarRepository
import javax.inject.Inject

class GetGameLogUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository
) {

    suspend operator fun invoke(
        year: Int,
        month: Int,
    ): BaseModelContent<GameDayListContent> {
        return calendarRepository.getGameList(year, month)
    }
}