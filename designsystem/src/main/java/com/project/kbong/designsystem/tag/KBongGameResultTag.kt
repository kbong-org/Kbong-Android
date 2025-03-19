package com.project.kbong.designsystem.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongTypography

@Composable
fun KBongGameResultTag(
    gameResultType: GameResultType
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(gameResultType.backgroundColor)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 4.5.dp, vertical = 4.dp),
            text = gameResultType.tagName,
            style = KBongTypography.Caption3Bold,
            color = gameResultType.textColor
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewKBongGameResultTag() {
    val gameResultTypeWin = GameResultType.WIN
    val gameResultTypeLose = GameResultType.LOSE
    val gameResultTypeDraw = GameResultType.DRAW
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        KBongGameResultTag(
            gameResultType = gameResultTypeWin
        )

        KBongGameResultTag(
            gameResultType = gameResultTypeLose
        )

        KBongGameResultTag(
            gameResultType = gameResultTypeDraw
        )
    }

}