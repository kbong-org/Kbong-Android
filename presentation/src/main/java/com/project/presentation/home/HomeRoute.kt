package com.project.presentation.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.kbong.designsystem.calender.HorizontalCalendar
import com.project.kbong.designsystem.navigationbar.KBongTopBar
import com.project.kbong.designsystem.tab.KBongTabBar
import com.project.kbong.designsystem.theme.KBongGrayscaleGray0
import com.project.kbong.designsystem.theme.KBongTeamBears
import com.project.presentation.R
import com.project.presentation.utils.localDateToString
import java.util.Calendar

@Composable
fun HomeRoute(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        when(viewModel.sideEffect){

        }
    }

    HomeScreen(
        modifier = modifier,
        state = state,
        homeViewEvent = { event ->
            viewModel.intent(event)
        }
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier,
    state: HomeViewContract.HomeViewState,
    homeViewEvent: (HomeViewContract.HomeViewEvent) -> Unit
) {
    val homeTabTitleList = stringArrayResource(R.array.home_tab).toList()


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

        DateTopContent(
            currentMonth = "2",
            myTeam = "두산 베어스",
            teamColor = KBongTeamBears,
            onClickMonth = {}
        )

        Log.d(TAG, "HomeScreen: state ${state.historyDayContents}")
        Log.d(TAG, "HomeScreen: state ${state.currentDate}") // 2025-02-10

        HorizontalCalendar(
            currentDate = state.currentDate,
            selectedDate = state.selectedDate,
            historyDayContentList = state.historyDayContents,
            onSelectedDate = { selectedDate ->
                homeViewEvent(
                    HomeViewContract.HomeViewEvent.OnSelectedDate(selectedDate.localDateToString())
                )
            }
        )

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

