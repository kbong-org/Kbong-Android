package com.project.kbong.designsystem.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.domain.model.day.DailyGameLog
import com.project.kbong.designsystem.theme.KBongGrayscaleGray1
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2

@Composable
fun KBongGameStatus(
    modifier: Modifier = Modifier,
    dailyGameLog: DailyGameLog
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(KBongGrayscaleGray1)
            .border(1.dp, KBongGrayscaleGray2, RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            GameStatusHeader(
                startTimeStr = dailyGameLog.startTimeStr,
                stadiumDisplayName = dailyGameLog.stadiumDisplayName,
                status = dailyGameLog.status
            )

            Spacer(modifier = Modifier.height(10.dp))
            val awayIsWin =
                if (dailyGameLog.awayTeamScore == null || dailyGameLog.homeTeamScore == null) {
                    null
                } else {
                    dailyGameLog.awayTeamScore!! > dailyGameLog.homeTeamScore!!
                }

            GameScoreBodyContent(
                teamName = dailyGameLog.awayTeamDisplayName,
                isMyTeam = dailyGameLog.myTeamStatus == "AWAY",
                isWin = awayIsWin,
                score = dailyGameLog.awayTeamScore
            )

            Spacer(modifier = Modifier.height(8.dp))

            val homeIsWin =
                if (dailyGameLog.awayTeamScore == null || dailyGameLog.homeTeamScore == null) {
                    null
                } else {
                    dailyGameLog.awayTeamScore!! < dailyGameLog.homeTeamScore!!
                }

            GameScoreBodyContent(
                teamName = dailyGameLog.homeTeamDisplayName,
                isMyTeam = dailyGameLog.myTeamStatus == "HOME",
                isWin = homeIsWin,
                score = dailyGameLog.homeTeamScore
            )

        }
    }
}

@Composable
@Preview(showSystemUi = false, showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun PreviewKBongGameStatus() {
    val dailyGameLog = DailyGameLog(
        id = 6079,
        startTimeStr = "12:00",
        status = "CANCELLED",
        awayTeamDisplayName = "SSG",
        homeTeamDisplayName = "삼성",
        stadiumDisplayName = "대구",
        awayTeamScore = 3,
        homeTeamScore = 2,
        myTeamStatus = "AWAY"
    )
    KBongGameStatus(
        dailyGameLog = dailyGameLog
    )
}