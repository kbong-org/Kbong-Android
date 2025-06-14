package com.project.presentation.home.day

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.domain.model.day.DailyLog
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R
import com.project.presentation.utils.formatLocalDate
import java.time.LocalDate

private const val MAX_LOG_COUNT = 3

@Composable
fun DayHistoryContent(
    selectedDate: LocalDate,
    dailyLogList: List<DailyLog>,
    onClickAddHistory: () -> Unit,
    isAddIcon: Boolean,
    onClickLogItem: (logId: Long) -> Unit,
    myTeamDisplayName: String
) {
    val logCount = dailyLogList.size

    Log.d(TAG, "DayHistoryContent: $dailyLogList")
    Log.d(TAG, "DayHistoryContent selectedDate: $selectedDate")
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        DayHistoryHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 19.dp, horizontal = 16.dp),
            selectedDate = selectedDate,
            isAddIcon = logCount < MAX_LOG_COUNT,
            onClickAddHistory = onClickAddHistory
        )
        dailyLogList.forEach { item ->
            with(item) {
                DayHistory(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    awayTeam = awayTeamDisplayName,
                    homeTeam = homeTeamDisplayName,
                    stadium = stadiumFullName,
                    imageCount = item.imageList.size,
                    type = type,
                    imageList = imageList,
                    myTeamDisplayName = myTeamDisplayName,
                    onClick = { onClickLogItem(id) }
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                thickness = 1.dp,
                color = KBongGrayscaleGray2
            )
        }
    }
}

@Composable
fun DayHistoryHeader(
    modifier: Modifier,
    isAddIcon: Boolean,
    selectedDate: LocalDate,
    onClickAddHistory: () -> Unit
) {

    Row(
        modifier = modifier,
    ) {
        Text(
            text = selectedDate.formatLocalDate(),
            style = KBongTypography.Body1Normal
        )

        if (isAddIcon) {
            Spacer(modifier = Modifier.weight(1F))
            Image(
                modifier = Modifier.clickable { onClickAddHistory() },
                painter = painterResource(R.drawable.add_icon),
                contentDescription = "add_icon"
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewDayHistoryHeader() {
    DayHistoryContent(
        selectedDate = LocalDate.of(2025, 2, 17),
        onClickAddHistory = {},
        dailyLogList = listOf(),
        isAddIcon = true,
        myTeamDisplayName = "LG",
        onClickLogItem = {}
    )
}