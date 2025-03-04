package com.project.kbong.designsystem.calender

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.domain.model.calender.HistoryDayContent
import com.project.kbong.designsystem.R
import com.project.kbong.designsystem.theme.KBongGrayscaleGray5
import com.project.kbong.designsystem.theme.KBongTypography
import java.time.LocalDate

@Composable
fun HorizontalCalendar(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    firstDayOfWeek: Int,
    historyDayContentList: List<HistoryDayContent>,
    onSelectedDate: (LocalDate) -> Unit
    ){
    val weekTextList = stringArrayResource(R.array.week_text).toList()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            weekTextList.forEach { text ->
                Text(
                    modifier = Modifier.weight(1F),
                    text = text,
                    textAlign = TextAlign.Center,
                    style = KBongTypography.CalenderHeader,
                    color = KBongGrayscaleGray5
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        CalendarMonthItem(
            modifier = Modifier.fillMaxWidth(),
            firstDayOfWeek = firstDayOfWeek,
            currentDate = currentDate,
            selectedDate = selectedDate,
            historyDayContentList = historyDayContentList,
            onSelectedDate = { selectedDate ->
                onSelectedDate(selectedDate)
            },
        )
    }
}