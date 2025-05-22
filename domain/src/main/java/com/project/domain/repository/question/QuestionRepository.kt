package com.project.domain.repository.question

import com.project.domain.model.question.ChoiceQuestion
import com.project.domain.model.question.ShortQuestion

interface QuestionRepository {
    suspend fun getShortQuestion(): Result<ShortQuestion>
    suspend fun getChoiceQuestions(): Result<List<ChoiceQuestion>>
    suspend fun getRefreshedChoiceQuestion(): Result<ChoiceQuestion>
}