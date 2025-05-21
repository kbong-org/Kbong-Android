package com.project.domain.usecase.question

import com.project.domain.model.question.ChoiceQuestion
import com.project.domain.repository.question.QuestionRepository
import javax.inject.Inject

class GetRefreshChoiceQuestionUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(): Result<ChoiceQuestion> {
        return questionRepository.getRefreshedChoiceQuestion()
    }
}