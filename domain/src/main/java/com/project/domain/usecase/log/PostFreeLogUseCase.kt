package com.project.domain.usecase.log

import com.project.domain.model.log.FreeLogRequest
import com.project.domain.repository.log.LogRepository
import javax.inject.Inject

class PostFreeLogUseCase @Inject constructor(
    private val logRepository: LogRepository
) {
    suspend operator fun invoke(request: FreeLogRequest): Result<Long> {
        return logRepository.postFreeLog(request)
    }
}