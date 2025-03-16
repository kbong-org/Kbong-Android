package com.project.presentation.home.day

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.domain.model.day.DailyGameLog
import com.project.kbong.designsystem.game.KBongGameStatus

@Composable
fun DayGameHistoryContent(
    modifier: Modifier = Modifier,
    dailyGameLogList: List<DailyGameLog>,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        dailyGameLogList.forEach { item ->
            KBongGameStatus(
                modifier = Modifier.padding(bottom = 12.dp),
                dailyGameLog = item
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewDayGameHistoryContent() {
    DayGameHistoryContent(
        dailyGameLogList = listOf()
    )
}