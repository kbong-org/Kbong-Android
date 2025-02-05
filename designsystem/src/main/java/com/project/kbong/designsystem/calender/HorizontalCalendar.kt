package com.project.kbong.designsystem.calender

import android.content.ContentValues.TAG
import android.icu.util.Calendar
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.R
import com.project.kbong.designsystem.theme.KBongGrayscaleGray5
import com.project.kbong.designsystem.theme.KBongTypography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalCalendar(
    modifier: Modifier = Modifier,
    onSelectedDate: (Calendar) -> Unit
    ){

    val calender = Calendar.getInstance()
    val weekTextList = stringArrayResource(R.array.week_text).toList()

    var currentMonth by remember {
        mutableStateOf(calender.apply {
            firstDayOfWeek = Calendar.SUNDAY // 일요일 시작으로 설정
        })
    }

    var currentPage by remember {
        val currentYear = currentMonth.get(Calendar.YEAR)
        val currentMonthValue = currentMonth.get(Calendar.MONTH)
        val monthsSince1970 = (currentYear - 1970) * 12 + currentMonthValue
        mutableIntStateOf(monthsSince1970)
    }
    var currentSelectedDate by remember {
        mutableStateOf(calender)
    }

    // 1970부터 2100까지의 총 월 수 계산
    val totalMonths = (2100 - 1970) * 12

    val pagerState = rememberPagerState(
        initialPage = currentPage,
        pageCount = { totalMonths }
    )

    LaunchedEffect(pagerState.currentPage) {
        val addMonth = (pagerState.currentPage - currentPage)
        val newCalendar = currentMonth.clone() as Calendar
        newCalendar.add(Calendar.MONTH, addMonth)
        currentMonth = newCalendar
        currentPage = pagerState.currentPage
    }

    LaunchedEffect(currentSelectedDate) {
        onSelectedDate(currentSelectedDate)
    }

    Log.d(TAG, "HorizontalCalendar: currentMonth ${currentMonth.time}")
    Log.d(TAG, "HorizontalCalendar: currentSelectedDate ${currentSelectedDate.time}")
    Log.d(TAG, "HorizontalCalendar: currentPage $currentPage")

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

        HorizontalPager(
            state = pagerState
        ) { page ->
            val inCalendar = calender.apply {
                val relativePosition = page - currentPage
                set(Calendar.YEAR, currentMonth.get(Calendar.YEAR) + relativePosition / 12)
                set(Calendar.MONTH, page % 12 + 1)
                set(Calendar.DAY_OF_MONTH, 1)
            }
            if (page in pagerState.currentPage - 1..pagerState.currentPage + 1) {
                CalendarMonthItem(
                    modifier = Modifier.fillMaxWidth(),
                    currentDate = inCalendar,
                    selectedDate = currentSelectedDate,
                    onSelectedDate = { date ->
                        currentSelectedDate = date
                    }
                )
            }
        }
    }
}