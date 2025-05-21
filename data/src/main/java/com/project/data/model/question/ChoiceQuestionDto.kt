package com.project.data.model.question

import com.project.domain.model.question.ChoiceQuestion
import com.project.domain.model.question.AnswerOption

data class ChoiceQuestionDto(
    val questionId: Long,
    val questionText: String,
    val answerOptions: List<AnswerOptionDto>
) {
    fun toDomain(): ChoiceQuestion = ChoiceQuestion(
        questionId = questionId,
        questionText = questionText,
        answerOptions = answerOptions.map { it.toDomain() }
    )
}

data class AnswerOptionDto(
    val id: Long,
    val text: String
) {
    fun toDomain(): AnswerOption = AnswerOption(id, text)
}