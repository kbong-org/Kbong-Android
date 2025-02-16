package com.project.kbong.designsystem.calender


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.project.kbong.designsystem.theme.KBongTypography
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
        LocalDate.of(2024, 6, 21) == conversionLocalDate -> KBongGrayscaleGray1
        // DateUtil.toDay() == conversionLocalDate -> KBongGrayscaleGray1
        else -> KBongGrayscaleGray0
    }
    Column(
        modifier = modifier
            .defaultMinSize(minHeight = 48.dp)
            .clickable { onSelectedDate() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(200.dp))
                .background(currentDateColor)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                text = historyDayContent.day,
                style = KBongTypography.Label1Normal,
                color = if (isSelected) KBongGrayscaleGray0 else KBongTeamGray10
            )
        }

        if (
            historyDayContent.emotion.isNullOrEmpty() &&
            //DateUtil.toDay() <= selectedDate
            LocalDate.of(2024, 6, 21) >= conversionLocalDate
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 14.dp, bottom = 10.dp)
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(KBongGrayscaleGray2)
                    .align(Alignment.CenterHorizontally)
            ) {}
        } else {
            AsyncImage(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally),
                model = historyDayContent.emotion,
                contentDescription = "emotion"
            )
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun PreviewCalendarDay() {
    CalendarDay(
        selectedDate = LocalDate.now(),
        conversionLocalDate = LocalDate.of(2024, 6, 21),
        historyDayContent = HistoryDayContent("10", true, null),
        onSelectedDate = {}
    )
}