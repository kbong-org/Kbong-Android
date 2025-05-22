package com.project.domain.usecase.log

import com.project.domain.model.day.DailyGameLog
import com.project.domain.repository.DayRepository
import java.time.LocalDate
import javax.inject.Inject

class GetGamesForLogUseCase @Inject constructor(
    private val repository: DayRepository
) {
    suspend operator fun invoke(date: LocalDate): Result<List<DailyGameLog>> {
        return repository.getGameListForLog(date)
    }
}