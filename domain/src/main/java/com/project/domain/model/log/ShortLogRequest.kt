package com.project.domain.model.log

data class ShortLogRequest(
    val gameId: Long,
    val questionId: Long,
    val text: String,
    val emotion: Emotion,
    val imagePaths: List<String>
)