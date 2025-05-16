package com.project.data.model.log

import android.os.Parcelable
import com.project.domain.model.day.DailyGameLog
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DailyGameLogDto(
    val id: Long,
    val startTimeStr: String,
    val stadiumDisplayName: String,
    val status: String,
    val awayTeamDisplayName: String,
    val homeTeamDisplayName: String,
    val awayTeamScore: Int? = null,
    val homeTeamScore: Int? = null
) : Parcelable

fun DailyGameLogDto.toDomain(): DailyGameLog {
    return DailyGameLog(
        id = id,
        startTimeStr = startTimeStr,
        status = status,
        awayTeamDisplayName = awayTeamDisplayName,
        homeTeamDisplayName = homeTeamDisplayName,
        stadiumDisplayName = stadiumDisplayName,
        awayTeamScore = awayTeamScore,
        homeTeamScore = homeTeamScore,
        myTeamStatus = "" // 서버 응답엔 없지만 도메인에 존재 → 빈 문자열로 처리
    )
}

fun DailyGameLog.toDto(): DailyGameLogDto {
    return DailyGameLogDto(
        id = id,
        startTimeStr = startTimeStr,
        status = status,
        awayTeamDisplayName = awayTeamDisplayName,
        homeTeamDisplayName = homeTeamDisplayName,
        stadiumDisplayName = stadiumDisplayName,
        awayTeamScore = awayTeamScore,
        homeTeamScore = homeTeamScore
    )
}