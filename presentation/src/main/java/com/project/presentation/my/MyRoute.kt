package com.project.presentation.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.data.LocalNavController
import com.project.domain.model.user.UserInfoContent
import com.project.presentation.my.ui.CATALOG
import com.project.presentation.my.ui.LIST
import com.project.presentation.my.ui.MyBottomContent
import com.project.presentation.my.ui.MyTopBar
import com.project.presentation.my.ui.MyTopContent
import com.project.presentation.my.ui.VisitedGameContent
import com.project.presentation.setting.navigateToSetting
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MyRoute(
    viewModel: MyViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                MyContract.MyViewSideEffect.NavigateToSetting -> {
                    navController.navigateToSetting()
                }

                else -> Unit
            }
        }
    }
    MyScreen(
        state = state,
        event = { event ->
            viewModel.intent(event)
        }
    )

}

@Composable
fun MyScreen(
    state: MyContract.MyViewState,
    event: (MyContract.MyViewEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(state.myTeamType.backgroundColor)
        ) {
            MyTopBar(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 18.dp),
                onClickSetting = {
                    event(MyContract.MyViewEvent.OnClickSetting)
                }
            )
            Box(

            ) {
                MyTopContent(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 29.dp
                    ),
                    myTeamType = state.myTeamType,
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

            MyBottomContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp),
                dailyLog = state.userInfoContent.dailyLog,
                isCatalogSelect = state.selectViewType == CATALOG,
                isListSelect = state.selectViewType == LIST,
                onClickViewType = { type ->
                    event.invoke(
                        MyContract.MyViewEvent.OnClickSelectViewType(type)
                    )
                }
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
        event = {}
    )
}