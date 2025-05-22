package com.project.data.model.log

import com.project.domain.model.log.Emotion

data class ChoiceLogRequestDto(
    val gameId: Long,
    val answerItems: List<AnswerItemDto>,
    val emotion: Emotion,
    val imagePaths: List<String>
)

data class AnswerItemDto(
    val questionId: Long,
    val answerOptionId: Long
)
