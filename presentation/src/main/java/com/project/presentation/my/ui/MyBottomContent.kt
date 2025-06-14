package com.project.presentation.my.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.domain.model.user.MyPageDailyLog
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongPrimary10
import com.project.presentation.home.day.EmptyDayHistoryContent

const val CATALOG = "CATALOG"
const val LIST = "LIST"

@Composable
fun MyBottomContent(
    modifier: Modifier = Modifier,
    dailyLog: List<MyPageDailyLog>,
    isCatalogSelect: Boolean = false,
    isListSelect: Boolean = false,
    onClickViewType: (String) -> Unit
) {

    Column(
        modifier = modifier
    ) {
        BottomHeaderContent(
            isCatalogSelect = isCatalogSelect,
            isListSelect = isListSelect,
            onClickViewType = onClickViewType
        )
        if (dailyLog.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F),
                contentAlignment = Alignment.Center
            ) {
                EmptyDayHistoryContent(
                    isGoLogButton = false,
                    // todo teamColor, teamColorBg 팀 컬러에 맞게 받아오도록 해야함
                    teamColor = KBongPrimary,
                    teamColorBg = KBongPrimary10
                )
            }

        } else {
            when {
                isCatalogSelect -> {
                    MyBottomCatalogMainContent(
                        dailyLog = dailyLog
                    )
                }

                isListSelect -> {
                    val imageList = dailyLog.flatMap { dailyLog ->
                        dailyLog.logs.map {
                            it.imagePath
                        }
                    }

                    MyBottomListMainContent(
                        modifier = Modifier.padding(),
                        imagePathList = imageList
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMyBottomContent() {
    MyBottomContent(
        dailyLog = listOf(
            /* MyPageDailyLog(
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
             )*/
        ),
        onClickViewType = {}
    )
}