package com.project.presentation.setting.edit

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.data.LocalNavController
import com.project.kbong.designsystem.KBongSnackbar
import com.project.kbong.designsystem.component.BaseSettingContent
import com.project.kbong.designsystem.navigationbar.KBongTopBar
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R
import com.project.presentation.my.MyContract
import com.project.presentation.my.MyViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileEditRoute(
    viewModel: MyViewModel
) {
    val state by viewModel.state.collectAsState()
    val navController: NavController = LocalNavController.current
    var profileEditType: ProfileEditType by remember {
        mutableStateOf(ProfileEditType.NONE)
    }
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                MyContract.MyViewSideEffect.NavigateToBack -> {
                    navController.popBackStack()
                }

                is MyContract.MyViewSideEffect.ChangeProfileEditType -> {
                    profileEditType = sideEffect.type
                }

                MyContract.MyViewSideEffect.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(state.snackbarMessage)
                }

                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (profileEditType) {
            ProfileEditType.NICKNAME -> {
                NicknameEditScreen(
                    nickname = state.nickname,
                    onTextChange = {
                        viewModel.intent(
                            MyContract.MyViewEvent.ProfileEditEvent.OnChangedNickname(it)
                        )
                    },
                    onClickBackButton = {
                        viewModel.intent(
                            MyContract.MyViewEvent.OnClickBack
                        )
                    },
                    onClickNicknameSave = {
                        viewModel.intent(
                            MyContract.MyViewEvent.ProfileEditEvent.OnClickNicknameSave(
                                context.getString(R.string.nickname_save_snackbar)
                            )
                        )
                    }

                )
            }

            ProfileEditType.SUPPORT_TEAM -> {}
            ProfileEditType.BIRTHDAY -> {}
            ProfileEditType.GENDER -> {}
            ProfileEditType.NONE -> {
                ProfileEditScreen(
                    state = state,
                    event = { viewModel.intent(it) }
                )
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    bottom = 20.dp,
                )
        ) {
            KBongSnackbar(
                text = state.snackbarMessage
            )
        }
    }


}

@Composable
fun ProfileEditScreen(
    state: MyContract.MyViewState,
    event: (MyContract.MyViewEvent) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        KBongTopBar(
            titleText = stringResource(R.string.my_info_edit),
            onClickBackButton = {
                event(
                    MyContract.MyViewEvent.OnClickBack
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            painter = painterResource(state.myTeamType.infoImage),
            contentDescription = "infoImage"
        )

        BaseSettingContent(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    event(
                        MyContract.MyViewEvent.ProfileEditEvent.OnClickEditMenu(
                            ProfileEditType.NICKNAME
                        )
                    )
                }
                .padding(start = 20.dp, end = 20.dp, top = 32.dp, bottom = 40.dp),
            leftContent = {
                Text(
                    text = stringResource(R.string.nickname),
                    style = KBongTypography.Heading2Medium,
                    color = KBongGrayscaleGray8
                )
            }
        )

        BaseSettingContent(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                }
                .padding(horizontal = 20.dp, vertical = 32.dp),
            leftContent = {
                Text(
                    text = stringResource(R.string.support_team),
                    style = KBongTypography.Heading2Medium,
                    color = KBongGrayscaleGray8
                )
            }
        )

        BaseSettingContent(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                }
                .padding(horizontal = 20.dp, vertical = 32.dp),
            leftContent = {
                Text(
                    text = stringResource(R.string.birthday),
                    style = KBongTypography.Heading2Medium,
                    color = KBongGrayscaleGray8
                )
            }
        )

        BaseSettingContent(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                }
                .padding(horizontal = 20.dp, vertical = 32.dp),
            leftContent = {
                Text(
                    text = stringResource(R.string.gender),
                    style = KBongTypography.Heading2Medium,
                    color = KBongGrayscaleGray8
                )
            }
        )
    }
}

@Preview
@Composable
private fun PreviewProfileEditScreen() {
    ProfileEditScreen(
        state = MyContract.MyViewState(),
        event = {}
    )
}