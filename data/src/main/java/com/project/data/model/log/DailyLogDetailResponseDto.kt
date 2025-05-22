package com.project.data.model.log

data class DailyLogDetailResponseDto(
    val id: Long,
    val emotion: String,
    val type: String,
    val gameInfo: GameInfoDto,
    val imagePaths: List<String>,
    val detail: DetailDto?
)

data class GameInfoDto(
    val id: Long,
    val awayTeamDisplayName: String,
    val homeTeamDisplayName: String,
    val stadiumFullName: String,
    val date: String
)

data class DetailDto(
    val text: String?,
    val questionId: Int?,
    val questionText: String?,
    val answer: String?,
    val selectedOption: String?,
    val answers: List<AnswerDto>?
)

data class AnswerDto(
    val questionId: Int,
    val questionText: String,
    val answerOptionId: Int,
    val answerOptionText: String
)