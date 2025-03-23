package com.project.presentation.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.domain.model.user.UserInfoContent
import com.project.presentation.my.ui.MyTopBar
import com.project.presentation.my.ui.MyTopContent
import com.project.presentation.my.ui.VisitedGameContent

@Composable
fun MyRoute(
    viewModel: MyViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val myTeamType = MyTeamType.fromTypeData(state.userInfoContent.myTeam.code)
    MyScreen(
        state = state,
        myTeamType = myTeamType
    )
}

@Composable
fun MyScreen(
    state: MyContract.MyViewState,
    myTeamType: MyTeamType
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(myTeamType.backgroundColor)
        ) {
            MyTopBar(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 18.dp),
                onClickSetting = {}
            )
            Box(

            ) {
                MyTopContent(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 29.dp
                    ),
                    myTeamType = myTeamType,
                    nickname = state.userInfoContent.nickname,
                    visitedGames = state.userInfoContent.visitedGames
                )
            }

            VisitedGameContent(
                modifier = Modifier.padding(
                    top = 24.dp,
                    bottom = 30.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
                visitedGames = state.userInfoContent.visitedGames
            )


        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewMyScreen() {

    val state = MyContract.MyViewState(
        isError = false,
        userInfoContent = UserInfoContent()
    )
    MyScreen(
        state = state,
        myTeamType = MyTeamType.KIA
    )
}