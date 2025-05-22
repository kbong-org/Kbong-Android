package com.project.domain.usecase.log

import com.project.domain.model.log.DailyLogDetail
import com.project.domain.repository.log.LogRepository
import javax.inject.Inject

class GetLogDetailUseCase @Inject constructor(
    private val repository: LogRepository
) {
    suspend operator fun invoke(id: Long): DailyLogDetail {
        return repository.getLogDetail(id)
    }
}