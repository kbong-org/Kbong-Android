package com.project.kbong.designsystem.calender

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

    // 전체 아이템 수 계산 (빈 공간 + 실제 날짜)
    val totalItems = firstDayOfWeek + historyDayContentList.size
    // 필요한 행 수 계산 (7일씩 표시)
    val rows = (totalItems + 6) / 7

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (row in 0 until rows) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                for (col in 0 until 7) {
                    val index = row * 7 + col
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (index in firstDayOfWeek..<totalItems) {
                            val contentIndex = index - firstDayOfWeek
                            val historyDayContent = historyDayContentList[contentIndex]
                            val conversionLocalDate = LocalDate.of(
                                currentDate.year,
                                currentDate.month,
                                historyDayContent.day.toInt()
                            )
                            CalendarDay(
                                modifier = Modifier.align(Alignment.Center),
                                selectedDate = selectedDate,
                                historyDayContent = historyDayContent,
                                conversionLocalDate = conversionLocalDate,
                                onSelectedDate = {
                                    onSelectedDate(conversionLocalDate)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
