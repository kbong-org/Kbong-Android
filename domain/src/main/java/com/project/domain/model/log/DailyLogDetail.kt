package com.project.domain.model.log

data class DailyLogDetail(
    val id: Long,
    val emotion: String,
    val type: String, // "FREE", "SUBJECTIVE", "OBJECTIVE"
    val gameInfo: GameInfo,
    val imagePaths: List<String>,
    val detail: Detail?
)

data class GameInfo(
    val id: Long,
    val awayTeamDisplayName: String,
    val homeTeamDisplayName: String,
    val stadiumFullName: String,
    val date: String
)

data class Detail(
    val text: String?, // 자유형
    val questionId: Int?, // 주관형
    val questionText: String?,
    val answer: String?,  // 주관형
    val selectedOption: String?, //
    val answers: List<Answer>?
) {
    data class Answer(
        val questionId: Int,
        val questionText: String,
        val answerOptionId: Int,
        val answerOptionText: String
    )
}