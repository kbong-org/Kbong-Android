package com.project.presentation.my.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.presentation.R

@Composable
fun MyTopBar(
    modifier: Modifier = Modifier,
    onClickSetting: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(width = 83.dp, height = 22.dp),
            painter = painterResource(com.project.kbong.designsystem.R.drawable.kbong_img),
            contentDescription = "kbong"
        )
        Spacer(modifier = Modifier.weight(1F))
        Image(
            modifier = Modifier.clickable { onClickSetting() },
            painter = painterResource(R.drawable.ic_setting),
            contentDescription = "setting"
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewMyTopBar() {
    MyTopBar(
        modifier = Modifier.padding(20.dp),
        onClickSetting = {}
    )
}