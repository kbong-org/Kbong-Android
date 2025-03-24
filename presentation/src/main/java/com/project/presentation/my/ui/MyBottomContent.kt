package com.project.presentation.my.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.domain.model.user.Logs
import com.project.domain.model.user.MyPageDailyLog

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
        MyBottomMainContent(
            dailyLog = dailyLog
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMyBottomContent() {
    MyBottomContent(
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
        onClickViewType = {}
    )
}