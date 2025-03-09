package com.project.presentation.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.kbong.designsystem.calendar.HorizontalCalendar
import com.project.kbong.designsystem.datepicker.DatePickerModal
import com.project.kbong.designsystem.navigationbar.KBongTopBar
import com.project.kbong.designsystem.tab.KBongTabBar
import com.project.kbong.designsystem.theme.KBongGrayscaleGray0
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongTeamBears
import com.project.kbong.designsystem.utils.DateUtil.today
import com.project.presentation.R
import com.project.presentation.home.day.DayHistoryContent
import com.project.presentation.home.day.EmptyDayHistoryContent
import com.project.presentation.home.day.EmptyGameContent
import com.project.presentation.utils.localDateToString

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    var isShowDatePicker by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                HomeViewContract.HomeViewSideEffect.ShowDatePicker -> {
                    isShowDatePicker = true
                }
            }
        }

    }
    Box(modifier = Modifier.fillMaxSize()) {
        HomeScreen(
            modifier = modifier,
            state = state,
            homeViewEvent = { event ->
                viewModel.intent(event)
            }
        )
        if (isShowDatePicker) {
            DatePickerModal(
                selectedDate = state.selectedDate,
                onDateSelected = { date ->
                    viewModel.intent(
                        event = HomeViewContract.HomeViewEvent.OnSelectedDate(
                            (date ?: today()).localDateToString()
                        )
                    )
                },
                onDismiss = {
                    isShowDatePicker = false
                }
            )
        }

    }

}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeViewContract.HomeViewState,
    homeViewEvent: (HomeViewContract.HomeViewEvent) -> Unit,
) {
    val homeTabTitleList = stringArrayResource(R.array.home_tab).toList()
    val hasGame = state.historyDayContents.firstOrNull {
        it.day.toInt() == state.selectedDate.dayOfMonth
    }?.hasGame ?: false

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        KBongTopBar(
            modifier = Modifier.background(KBongGrayscaleGray0),
            leftContent = {
                Image(
                    modifier = Modifier.size(width = 41.dp, height = 21.dp),
                    painter = painterResource(id = R.drawable.kbong),
                    contentDescription = ""
                )
            },
            isBackButton = false,
            rightContent = {
                Image(
                    painter = painterResource(id = R.drawable.tip_off),
                    contentDescription = ""
                )
            }
        )

        KBongTabBar(
            titleList = homeTabTitleList,
            selectedTitle = state.selectTab,
            onClickItem = {
                homeViewEvent(
                    HomeViewContract.HomeViewEvent.OnTabSelected(it)
                )
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {

            item {
                DateTopContent(
                    currentMonth = "${state.selectedDate.monthValue}",
                    myTeam = state.myTeamDisplayName,
                    teamColor = KBongTeamBears,
                    onClickMonth = {
                        homeViewEvent(
                            HomeViewContract.HomeViewEvent.OnClickMonth
                        )
                    }
                )
            }

            item {
                Log.d(TAG, "HomeScreen: state ${state.historyDayContents}")
                Log.d(TAG, "HomeScreen: state ${state.currentDate}")
                Log.d(TAG, "HomeScreen: state ${state.selectedDate}")
                Log.d(TAG, "HomeScreen: state ${state.gameDayContents}")

                HorizontalCalendar(
                    selectedDate = state.selectedDate,
                    historyDayContentList = state.historyDayContents,
                    onSelectedDate = { selectedDate ->
                        homeViewEvent(
                            HomeViewContract.HomeViewEvent.OnSelectedDate(selectedDate.localDateToString())
                        )
                    }
                )
            }

            item {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp),
                    thickness = 6.dp,
                    color = KBongGrayscaleGray2
                )
            }

            item {
                when {
                    !hasGame -> {
                        EmptyGameContent()
                    }

                    state.dailyLogList.isEmpty() -> {
                        EmptyDayHistoryContent(
                            onClickGoLog = {
                                homeViewEvent(
                                    HomeViewContract.HomeViewEvent.OnClickAddHistory
                                )
                            }
                        )
                    }

                    else -> {
                        DayHistoryContent(
                            selectedDate = state.selectedDate,
                            dailyLogList = state.dailyLogList,
                            onClickAddHistory = {
                                homeViewEvent(
                                    HomeViewContract.HomeViewEvent.OnClickAddHistory
                                )
                            }
                        )
                    }
                }
            }
        }

    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(
        modifier = Modifier,
        state = HomeViewContract.HomeViewState(),
        {},
    )
}

