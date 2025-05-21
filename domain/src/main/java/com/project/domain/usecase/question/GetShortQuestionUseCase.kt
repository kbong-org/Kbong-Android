package com.project.domain.usecase.question

import com.project.domain.repository.question.QuestionRepository
import javax.inject.Inject

class GetShortQuestionUseCase @Inject constructor(
    private val repository: QuestionRepository
) {
    suspend operator fun invoke() = repository.getShortQuestion()
}