package com.project.kbong.designsystem.game

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.tag.GameStatusType
import com.project.kbong.designsystem.tag.KBongTag
import com.project.kbong.designsystem.theme.KBongGrayscaleGray10
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongTypography

@Composable
fun GameStatusHeader(
    startTimeStr: String,
    stadiumDisplayName: String,
    status: String,
) {
    val gameStatus = GameStatusType.fromTypeData(status)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = startTimeStr,
            style = KBongTypography.Body1NormalSemiBold,
            color = KBongGrayscaleGray10
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = stadiumDisplayName,
            style = KBongTypography.Label2Regular,
            color = KBongGrayscaleGray8
        )
        Spacer(modifier = Modifier.width(6.dp))
        KBongTag(
            textModifier = Modifier.padding(4.dp),
            roundedCornerShapeDp = 6,
            tagName = gameStatus.tagName,
            backgroundColor = gameStatus.backgroundColor,
            textColor = gameStatus.textColor
        )
    }
}

@Composable
@Preview
private fun PreviewGameStatusHeader() {
    GameStatusHeader(
        startTimeStr = "12:00",
        stadiumDisplayName = "대구",
        status = "CANCELLED"
    )
}