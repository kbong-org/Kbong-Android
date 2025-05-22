package com.project.domain.usecase.log

import com.project.domain.model.log.ShortLogRequest
import com.project.domain.repository.log.LogRepository
import javax.inject.Inject

class PostShortLogUseCase @Inject constructor(
    private val repository: LogRepository
) {
    suspend operator fun invoke(request: ShortLogRequest) = repository.postShortLog(request)
}