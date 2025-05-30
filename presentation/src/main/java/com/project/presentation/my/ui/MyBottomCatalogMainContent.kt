package com.project.presentation.my.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.domain.model.user.Logs
import com.project.domain.model.user.MyPageDailyLog
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongGrayscaleGray7
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.kbong.designsystem.utils.TeamNameMapper
import com.project.presentation.home.day.DayHistory
import com.project.presentation.utils.stringToFormatLocalData

@Composable
fun MyBottomCatalogMainContent(
    dailyLog: List<MyPageDailyLog>,
    myTeamDisplayName: String,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(dailyLog) { item ->
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .padding(horizontal =16.dp),
                text = item.date.stringToFormatLocalData(),
                style = KBongTypography.Body2Normal,
                color = KBongGrayscaleGray7
            )
            item.logs.forEach { log ->
                DayHistory(
                    modifier = Modifier.padding(0.dp),
                    awayTeam = log.awayTeamDisplayName,
                    homeTeam = log.homeTeamDisplayName,
                    stadium = log.stadiumFullName,
                    type = log.type,
                    imageCount = log.imageCount,
                    imageList = listOf(log.imagePath ?: ""),
                    myTeamDisplayName = TeamNameMapper.getDisplayName(myTeamDisplayName)
                )
            }
            Spacer(modifier = Modifier.height(17.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = KBongGrayscaleGray2
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewMyBottomMainContent() {
    MyBottomCatalogMainContent(
        dailyLog = listOf(
            MyPageDailyLog(
                date = "2025-03-24",
                logs = listOf(
                    Logs(
                        id = 4677,
                        awayTeamDisplayName = "삼성",
                        homeTeamDisplayName = "KT",
                        stadiumFullName = "수원 KT위즈 파크",
                        type = "FREE",
                        imagePath = "",
                        imageCount = 3
                    )
                )
            )
        ),
        myTeamDisplayName = "LG",
    )
}