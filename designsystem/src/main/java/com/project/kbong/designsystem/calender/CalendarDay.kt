package com.project.kbong.designsystem.calender


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.project.domain.model.calender.HistoryDayContent
import com.project.kbong.designsystem.theme.KBongGrayscaleGray0
import com.project.kbong.designsystem.theme.KBongGrayscaleGray1
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongTeamGray10
import com.project.kbong.designsystem.utils.DateUtil
import java.time.LocalDate

@Composable
fun CalendarDay(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    conversionLocalDate: LocalDate,
    historyDayContent: HistoryDayContent,
    onSelectedDate: () -> Unit
) {

    val isSelected = historyDayContent.day == selectedDate.dayOfMonth.toString()
    val currentDateColor = when{
        isSelected -> KBongPrimary
        DateUtil.toDay() == conversionLocalDate -> KBongGrayscaleGray1
        else -> KBongGrayscaleGray0
    }
    Column(
        modifier = modifier.clickable { onSelectedDate() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(200.dp))
                .background(currentDateColor)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = historyDayContent.day,
                color = if (isSelected) KBongGrayscaleGray0 else KBongTeamGray10
            )
        }

        if (
            historyDayContent.emotion.isNullOrEmpty() &&
            DateUtil.toDay() <= selectedDate
        ) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(200.dp))
                    .background(KBongGrayscaleGray2)
            ) {}
        } else {
            AsyncImage(
                model = historyDayContent.emotion,
                contentDescription = "emotion"
            )
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewCalendarDay() {
    CalendarDay(
        selectedDate = LocalDate.now(),
        conversionLocalDate = LocalDate.now(),
        historyDayContent = HistoryDayContent("1", true, null),
        onSelectedDate = {}
    )
}