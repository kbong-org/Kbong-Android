package com.project.presentation.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.data.LocalNavController
import com.project.kbong.designsystem.navigationbar.KBongTopBar
import com.project.kbong.designsystem.theme.KBongGrayscaleGray1
import com.project.presentation.R
import com.project.presentation.my.MyContract
import com.project.presentation.my.MyViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SettingRoute(
    viewModel: MyViewModel
) {

    val state by viewModel.state.collectAsState()
    val navController: NavController = LocalNavController.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                MyContract.MyViewSideEffect.NavigateToBack -> {
                    navController.popBackStack()
                }

                else -> Unit
            }
        }
    }

    SettingScreen(
        state = state,
        event = { viewModel.intent(it) }
    )
}

@Composable
fun SettingScreen(
    state: MyContract.MyViewState,
    event: (MyContract.MyViewEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        KBongTopBar(
            titleText = stringResource(R.string.setting),
            onClickBackButton = {
                event(
                    MyContract.MyViewEvent.OnClickBack
                )
            }
        )

        ProfileContent(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                }
                .padding(horizontal = 20.dp, vertical = 24.dp),
            nickName = state.userInfoContent.nickname,
            myTeamType = state.myTeamType,
        )

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 6.dp,
            color = KBongGrayscaleGray1
        )

        VersionContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp),
            versionCode = "2.10.11"
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 6.dp,
            color = KBongGrayscaleGray1
        )
    }
}

@Preview
@Composable
private fun PreviewSettingRoute() {
    SettingRoute(
        viewModel = hiltViewModel()
    )
}