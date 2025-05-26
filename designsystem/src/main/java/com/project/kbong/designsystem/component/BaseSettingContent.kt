package com.project.kbong.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun BaseSettingContent(
    modifier: Modifier = Modifier,
    leftContent: @Composable RowScope.() -> Unit = {},
    rightContent: @Composable RowScope.() -> Unit = {}
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        leftContent()
        Spacer(modifier = Modifier.weight(1F))
        rightContent()
        Image(
            painter = painterResource(com.project.kbong.designsystem.R.drawable.right_arrow),
            contentDescription = "right arrow"
        )
    }
}