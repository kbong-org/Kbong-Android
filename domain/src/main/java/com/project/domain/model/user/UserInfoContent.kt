package com.project.domain.model.user

data class UserInfoContent(
    val myTeam: MyTeam = MyTeam(),
    val nickname: String = "",
    val birthDate: String? = null,
    val gender: String? = null,
    val visitedGames: VisitedGames = VisitedGames(),
    val dailyLog: List<MyPageDailyLog> = listOf()
)


