package com.project.data.model.question

import com.project.domain.model.question.ShortQuestion

data class ShortQuestionDto(
    val questionId: Long,
    val questionText: String
) {
    fun toDomain(): ShortQuestion = ShortQuestion(
        questionId = questionId,
        questionText = questionText
    )
}