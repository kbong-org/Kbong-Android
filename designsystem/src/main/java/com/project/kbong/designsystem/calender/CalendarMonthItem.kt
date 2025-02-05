package com.project.kbong.designsystem.calender

import android.content.ContentValues.TAG
import android.icu.util.Calendar
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CalendarMonthItem(
    modifier: Modifier,
    currentDate: Calendar,
    selectedDate: Calendar,
    onSelectedDate: (Calendar) -> Unit
) {

    val lastDay by remember {
        mutableIntStateOf(currentDate.getActualMaximum(Calendar.DAY_OF_MONTH))
    }
    val firstDayOfWeek by remember {
        mutableIntStateOf(currentDate.get(Calendar.DAY_OF_WEEK) - 1)
    }
    val days by remember {
        mutableStateOf(IntRange(1, lastDay).toList())
    }

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

        items(days) { day ->
            // 이번 달의 날짜를 day로 치환하여 CalendarDay로 넘긴다.
            val calender = Calendar.getInstance().withDayOfMonth(day)
            Log.d(TAG, "items: calender ${calender.get(Calendar.DAY_OF_MONTH)}")

            CalendarDay(
                calendar = calender,
                isWin = true,
                isSelected = selectedDate.get(Calendar.DAY_OF_MONTH) == day,
                onSelectedDate = {
                    onSelectedDate(calender)
                }
            )
        }

    }

}

fun Calendar.withDayOfMonth(day: Int): Calendar {
    this.set(Calendar.DAY_OF_MONTH, day)
    return this
}