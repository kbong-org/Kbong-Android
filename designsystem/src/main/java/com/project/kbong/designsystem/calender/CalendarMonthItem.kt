package com.project.kbong.designsystem.calender

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.domain.model.calender.HistoryDayContent
import java.time.LocalDate

@Composable
fun CalendarMonthItem(
    modifier: Modifier,
    firstDayOfWeek: Int,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    historyDayContentList: List<HistoryDayContent>,
    onSelectedDate: (LocalDate) -> Unit
) {


    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(7),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // 처음 날짜가 시작하는 요일 전까지 빈 박스를 생성한다.
        for (i in 0 until firstDayOfWeek) {
            item {
                Box {}
            }
        }

        items(historyDayContentList) { historyDayContent ->
            val conversionLocalDate = LocalDate.of(
                currentDate.year,
                currentDate.month,
                historyDayContent.day.toInt()
            )
            CalendarDay(
                selectedDate = selectedDate,
                historyDayContent = historyDayContent,
                conversionLocalDate = conversionLocalDate,
                onSelectedDate = {
                    onSelectedDate(
                        conversionLocalDate
                    )
                }
            )
        }
    }
}
