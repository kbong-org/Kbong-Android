package com.project.data.model.question

import com.project.domain.model.question.ChoiceQuestion

data class ChoiceQuestionListDto(
    val items: List<ChoiceQuestionDto>
) {
    fun toDomain(): List<ChoiceQuestion> = items.map { it.toDomain() }
}