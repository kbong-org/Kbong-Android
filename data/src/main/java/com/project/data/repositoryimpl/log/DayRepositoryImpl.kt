package com.project.data.repositoryimpl.log

import com.project.data.model.log.DailyGameLogDto
import com.project.data.model.log.toDomain
import com.project.data.service.LogService
import com.project.domain.model.day.DailyGameLog
import com.project.domain.repository.DayRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DayRepositoryImpl @Inject constructor(
    private val dayApi: LogService
) : DayRepository {

    override suspend fun getGameListForLog(date: LocalDate): Result<List<DailyGameLog>> {
        return runCatching {
            val formattedDate = date.format(DateTimeFormatter.ISO_LOCAL_DATE)
            val response = dayApi.getGamesForLog(formattedDate)

            if (response.isSuccess) {
                val items: List<DailyGameLogDto> = response.data?.dateGames
                    ?: throw Exception("경기 리스트가 비어있습니다")

                return@runCatching items.map { it.toDomain() }
            } else {
                throw Exception(response.errorResponse.message)
            }
        }
    }
}