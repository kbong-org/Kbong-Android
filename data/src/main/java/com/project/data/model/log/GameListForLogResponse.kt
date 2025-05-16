package com.project.data.model.log

import com.google.gson.annotations.SerializedName

data class GameListForLogResponse(
    @SerializedName("dateGames")
    val dateGames: List<DailyGameLogDto> = emptyList()
)

data class DateGamesData(
    val dateGames: List<DailyGameLogDto>? = null
)

data class ErrorResponse(
    val code: String,
    val message: String
)