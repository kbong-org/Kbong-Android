package com.project.domain.model.log

data class ChoiceLogRequest(
    val gameId: Long,
    val answerItems: List<AnswerItem>,
    val emotion: Emotion,
    val imagePaths: List<String>
) {
    data class AnswerItem(
        val questionId: Long,
        val answerOptionId: Long
    )
}