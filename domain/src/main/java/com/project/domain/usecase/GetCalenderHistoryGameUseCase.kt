package com.project.domain.usecase

import com.project.domain.model.BaseModelContent
import com.project.domain.model.calender.HistoryDaysContent
import com.project.domain.repository.calender.CalenderRepository
import javax.inject.Inject

class GetCalenderHistoryGameUseCase @Inject constructor(
    private val calenderRepository: CalenderRepository
) {

    suspend operator fun invoke(year: Int, month: Int): BaseModelContent<List<HistoryDaysContent>> {
        return calenderRepository.getCalenderHistoryGame(
            year = year,
            month = month
        )
    }
}