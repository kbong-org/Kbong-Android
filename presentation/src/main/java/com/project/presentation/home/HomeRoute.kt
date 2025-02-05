package com.project.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.calender.HorizontalCalendar
import com.project.kbong.designsystem.navigationbar.KBongTopBar
import com.project.kbong.designsystem.tab.KBongTabBar
import com.project.kbong.designsystem.theme.KBongGrayscaleGray0
import com.project.kbong.designsystem.theme.KBongTeamBears
import com.project.presentation.R

@Composable
fun HomeRoute(
    // todo viewModel: HomeViewModel = hiltViewModel(),
) {

    HomeScreen(
        selectedTitle = "직관기록",
        onClickTab = { tab ->

        }
    )
}

@Composable
fun HomeScreen(
    selectedTitle: String,
    onClickTab: (String) -> Unit
) {
    val homeTabTitleList = stringArrayResource(R.array.home_tab).toList()

    Column(
        modifier = Modifier
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
            selectedTitle = selectedTitle,
            onClickItem = onClickTab
        )

        DateTopContent(
            currentMonth = "2",
            myTeam = "두산 베어스",
            teamColor = KBongTeamBears,
            onClickMonth = {}
        )


        HorizontalCalendar(
            onSelectedDate = {

            }
        )

    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(
        "직관기록",
        {}
    )
}

