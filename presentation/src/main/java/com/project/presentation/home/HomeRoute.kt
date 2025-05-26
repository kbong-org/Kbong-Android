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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.kbong.designsystem.calendar.HorizontalCalendar
import com.project.kbong.designsystem.datepicker.DatePickerModal
import com.project.kbong.designsystem.navigationbar.KBongTopBar
import com.project.kbong.designsystem.tab.KBongTabBar
import com.project.kbong.designsystem.theme.KBongGrayscaleGray0
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongPrimary10
import com.project.kbong.designsystem.theme.KBongTeamBears
import com.project.kbong.designsystem.theme.KBongTeamBears10
import com.project.kbong.designsystem.theme.KBongTeamEagles
import com.project.kbong.designsystem.theme.KBongTeamEagles10
import com.project.kbong.designsystem.theme.KBongTeamGiants
import com.project.kbong.designsystem.theme.KBongTeamGiants10
import com.project.kbong.designsystem.theme.KBongTeamGray10
import com.project.kbong.designsystem.theme.KBongTeamGray2
import com.project.kbong.designsystem.theme.KBongTeamHeroes
import com.project.kbong.designsystem.theme.KBongTeamHeroes10
import com.project.kbong.designsystem.theme.KBongTeamLions
import com.project.kbong.designsystem.theme.KBongTeamLions10
import com.project.kbong.designsystem.theme.KBongTeamNc
import com.project.kbong.designsystem.theme.KBongTeamNcSub10
import com.project.kbong.designsystem.theme.KBongTeamSsg
import com.project.kbong.designsystem.theme.KBongTeamSsg10
import com.project.kbong.designsystem.theme.KBongTeamTigers
import com.project.kbong.designsystem.theme.KBongTeamTigers10
import com.project.kbong.designsystem.theme.KBongTeamTwins
import com.project.kbong.designsystem.theme.KBongTeamTwins10
import com.project.kbong.designsystem.utils.DateUtil.today
import com.project.kbong.designsystem.utils.TeamColorMapper
import com.project.presentation.R
import com.project.presentation.home.day.BeforeDayHistoryContent
import com.project.presentation.home.day.DayGameHistoryContent
import com.project.presentation.home.day.DayHistoryContent
import com.project.presentation.home.day.EmptyDayHistoryContent
import com.project.presentation.home.day.EmptyGameContent
import com.project.presentation.log.navigateToLogDetail
import com.project.presentation.log.navigateToSelectGame
import com.project.presentation.utils.localDateToString
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state by viewModel.state.collectAsState()
    var isShowDatePicker by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.intent(HomeViewContract.HomeViewEvent.OnTabSelected(state.selectTab))
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                HomeViewContract.HomeViewSideEffect.ShowDatePicker -> {
                    isShowDatePicker = true
                }
                is HomeViewContract.HomeViewSideEffect.NavigateToSelectGame -> {
                    navController.navigateToSelectGame(
                        date = state.selectedDate,
                        myTeamDisplayName = state.myTeamDisplayName
                    )
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
            },
            navController = navController
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
    navController: NavController,
) {
    val homeTabTitleList = stringArrayResource(R.array.home_tab).toList()
    val selectedDay = state.selectedDate.dayOfMonth

    val hasGame = if (state.selectTab == stringResource(R.string.game_history)) {
        state.historyDayContents.firstOrNull { it.day.toInt() == selectedDay }?.hasGame ?: false
    } else {
        state.gameDayContents.firstOrNull { it.day.toInt() == selectedDay }?.hasGame ?: false
    }

    val teamColor = TeamColorMapper.getTextColor(state.myTeamDisplayName)
    val teamColorBg = TeamColorMapper.getBackgroundColor(state.myTeamDisplayName)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        KBongTopBar(
            modifier = Modifier.background(KBongGrayscaleGray0),
            leftContent = {
                Image(
                    modifier = Modifier.size(width = 83.dp, height = 22.dp),
                    painter = painterResource(id = com.project.kbong.designsystem.R.drawable.title),
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
                    teamColor = teamColor,
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
                    gameDayListContent = state.gameDayContents,
                    selectTab = state.selectTab,
                    isMyTeam = state.myTeamDisplayName.isNotEmpty(),
                    teamColor = teamColor,
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

                    state.dailyLogList.isEmpty() && state.selectTab == stringResource(R.string.game_history) -> {
                        val now = LocalDateTime.now()
                        val isBefore6AM = now.isBefore(state.selectedDate.atTime(LocalTime.of(6, 0)))

                        if (isBefore6AM) {
                            BeforeDayHistoryContent()
                        } else {
                            EmptyDayHistoryContent(
                                onClickGoLog = {
                                    homeViewEvent(
                                        HomeViewContract.HomeViewEvent.OnClickAddHistory
                                    )
                                    navController.navigateToSelectGame(
                                        date = state.selectedDate,
                                        myTeamDisplayName = state.myTeamDisplayName
                                    )
                                },
                                teamColor = teamColor,
                                teamColorBg = teamColorBg
                            )
                        }
                    }

                    // 직관기록 일별 컨텐츠
                    state.selectTab == stringResource(R.string.game_history) -> {
                        DayHistoryContent(
                            selectedDate = state.selectedDate,
                            dailyLogList = state.dailyLogList,
                            onClickAddHistory = {
                                homeViewEvent(
                                    HomeViewContract.HomeViewEvent.OnClickAddHistory
                                )
                            },
                            isAddIcon = state.dailyLogList.size < 3,
                            onClickLogItem = { logId ->
                                navController.navigateToLogDetail(logId, state.myTeamDisplayName)
                            },
                            myTeamDisplayName = state.myTeamDisplayName
                        )
                    }


                    // 일별 경기기록
                    else -> {
                        DayGameHistoryContent(
                            modifier = Modifier.padding(top = 20.dp),
                            dailyGameLogList = state.dailyGameLogList
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
        navController = NavController(context = LocalContext.current) // Mock NavController
    )
}
