package com.project.domain.model.question

data class ChoiceQuestion(
    val questionId: Long,
    val questionText: String,
    val answerOptions: List<AnswerOption>
)

data class AnswerOption(
    val id: Long,
    val text: String
)
