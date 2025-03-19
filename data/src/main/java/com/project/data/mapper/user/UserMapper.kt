package com.project.data.mapper.user

import com.project.data.model.user.LogsResponse
import com.project.data.model.user.MyPageDailyLogResponse
import com.project.data.model.user.MyTeamResponse
import com.project.data.model.user.UserInfoResponse
import com.project.data.model.user.VisitedGamesResponse
import com.project.domain.model.user.Logs
import com.project.domain.model.user.MyPageDailyLog
import com.project.domain.model.user.MyTeam
import com.project.domain.model.user.UserInfoContent
import com.project.domain.model.user.VisitedGames

fun UserInfoResponse.toDomain(): UserInfoContent {
    return UserInfoContent(
        myTeam = myTeam.toDomain(),
        nickname = nickname,
        birthDate = birthDate,
        gender = gender,
        visitedGames = visitedGames.toDomain(),
        dailyLog = dailyLog.map { it.toDomain() }
    )
}

private fun MyTeamResponse.toDomain(): MyTeam {
    return MyTeam(
        code = code,
        fullName = fullName
    )
}

private fun VisitedGamesResponse.toDomain(): VisitedGames {
    return VisitedGames(
        total = total,
        myTeamWins = myTeamWins,
        myTeamLosses = myTeamLosses,
        myTeamDraws = myTeamDraws,
        myTeamRate = myTeamRate,
    )
}

private fun MyPageDailyLogResponse.toDomain(): MyPageDailyLog {
    return MyPageDailyLog(
        date = date,
        logs = logs.map { it.toDomain() }
    )
}

private fun LogsResponse.toDomain(): Logs {
    return Logs(
        id = id,
        awayTeamDisplayName = awayTeamDisplayName,
        homeTeamDisplayName = homeTeamDisplayName,
        stadiumFullName = stadiumFullName,
        type = type,
        imagePath = imagePath,
        imageCount = imageCount
    )
}