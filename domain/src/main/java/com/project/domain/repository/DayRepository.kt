package com.project.domain.repository

import com.project.domain.model.day.DailyGameLog
import java.time.LocalDate

interface DayRepository {
    suspend fun getGameListForLog(date: LocalDate): Result<List<DailyGameLog>>
}