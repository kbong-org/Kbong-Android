package com.project.presentation.setting.edit

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.component.KBongLargeButton
import com.project.kbong.designsystem.navigationbar.KBongTopBar
import com.project.kbong.designsystem.textfield.BaseTextField
import com.project.presentation.R

@Composable
fun NicknameEditScreen(
    nickname: String = "",
    onTextChange: (String) -> Unit,
    onClickBackButton: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current
    val beforeNickname = remember { nickname }
    Log.d(TAG, "NicknameEditScreen: $beforeNickname")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        KBongTopBar(
            titleText = stringResource(R.string.nickname_edit),
            onClickBackButton = onClickBackButton
        )

        Spacer(modifier = Modifier.height(20.dp))

        BaseTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = nickname,
            onValueChange = onTextChange,
            interactionSource = interactionSource
        )

        Spacer(modifier = Modifier.height(16.dp))

        KBongLargeButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = stringResource(R.string.save),
            enabled = beforeNickname != nickname,
            onClick = {}
        )

    }
}

@Preview
@Composable
private fun PreviewNicknameEditScreen() {
    NicknameEditScreen(
        nickname = "",
        onClickBackButton = {},
        onTextChange = {}
    )

}