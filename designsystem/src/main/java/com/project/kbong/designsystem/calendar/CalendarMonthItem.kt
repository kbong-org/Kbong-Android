package com.project.kbong.designsystem.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.project.domain.model.calendar.GameDayContent
import com.project.domain.model.calendar.HistoryDayContent
import com.project.kbong.designsystem.R
import java.time.LocalDate

@Composable
fun CalendarMonthItem(
    modifier: Modifier,
    selectedDate: LocalDate,
    selectTab: String,
    isMyTeam: Boolean,
    historyDayContentList: List<HistoryDayContent>,
    gameDayListContent: List<GameDayContent>,
    onSelectedDate: (LocalDate) -> Unit
) {
    // 현재 월의 1일 계산
    val firstDayOfMonth = selectedDate.withDayOfMonth(1)
    // 해당 월의 1일의 요일 계산 (7: 일요일, 1: 월요일, ..., 6: 토요일)
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value
    // 일요일을 0으로, 나머지 요일을 1-6으로 조정
    val adjustedFirstDayOfWeek = if (firstDayOfWeek == 7) 0 else firstDayOfWeek
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
                        if (index in adjustedFirstDayOfWeek..<totalItems) {
                            val contentIndex = index - firstDayOfWeek

                            when (selectTab) {
                                stringResource(R.string.game_history) -> {
                                    val historyDayContent = historyDayContentList[contentIndex]
                                    val day = historyDayContent.day.toInt()

                                    val conversionLocalDate =
                                        calculateConversionDate(day, selectedDate)

                                    HistoryCalendarDay(
                                        modifier = Modifier.align(Alignment.Center),
                                        selectedDate = selectedDate,
                                        historyDayContent = historyDayContent,
                                        conversionLocalDate = conversionLocalDate,
                                        onSelectedDate = {
                                            onSelectedDate(conversionLocalDate)
                                        }
                                    )
                                }

                                stringResource(R.string.game_schedule) -> {
                                    val gameDayContent = gameDayListContent[contentIndex]
                                    val day = gameDayContent.day.toInt()

                                    val conversionLocalDate =
                                        calculateConversionDate(day, selectedDate)

                                    GameCalendarDay(
                                        modifier = Modifier.align(Alignment.Center),
                                        selectedDate = selectedDate,
                                        gameDayContent = gameDayContent,
                                        isMyTeam = isMyTeam,
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
    }
}


private fun calculateConversionDate(day: Int, selectedDate: LocalDate): LocalDate {
    val lastDayOfMonth = selectedDate.lengthOfMonth()
    return if (day <= lastDayOfMonth) {
        LocalDate.of(
            selectedDate.year,
            selectedDate.month,
            day
        )
    } else {
        LocalDate.of(
            selectedDate.year,
            selectedDate.month,
            lastDayOfMonth
        )
    }
}
