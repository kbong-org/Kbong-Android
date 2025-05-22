package com.project.domain.usecase.log

import com.project.domain.model.log.ChoiceLogRequest
import com.project.domain.repository.log.LogRepository
import javax.inject.Inject

class PostChoiceLogUseCase @Inject constructor(
    private val repository: LogRepository
) {
    suspend operator fun invoke(request: ChoiceLogRequest) = repository.postChoiceLog(request)
}
