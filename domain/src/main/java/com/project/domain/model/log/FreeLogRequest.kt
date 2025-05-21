package com.project.domain.model.log

data class FreeLogRequest(
    val gameId: Long,
    val text: String,
    val emotion: Emotion,
    val imagePaths: List<String>
)

enum class Emotion {
    HAPPY, NORMAL, SAD, ANGRY,
}