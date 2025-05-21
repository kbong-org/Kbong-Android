package com.project.data.model.log

import com.project.domain.model.log.Emotion

data class ShortLogRequestDto(
    val gameId: Long,
    val questionId: Long,
    val text: String,
    val emotion: Emotion,
    val imagePaths: List<String>
)