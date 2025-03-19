package com.project.kbong.designsystem.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.R
import com.project.kbong.designsystem.tag.GameResultType
import com.project.kbong.designsystem.tag.KBongTag
import com.project.kbong.designsystem.theme.KBongGrayscaleGray10
import com.project.kbong.designsystem.theme.KBongGrayscaleGray4
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongTypography


@Composable
fun GameScoreBodyContent(
    teamName: String,
    isMyTeam: Boolean,
    isWin: Boolean? = null,
    score: Int? = null,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = teamName,
            style = KBongTypography.Label1Normal,
            color = KBongGrayscaleGray10
        )
        if (isMyTeam) {
            Spacer(modifier = Modifier.width(2.dp))
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.my_team),
                contentDescription = "MyTeam"
            )
        }

        if (isWin == true) {
            Spacer(modifier = Modifier.width(2.dp))
            val winType = GameResultType.WIN
            KBongTag(
                textModifier = Modifier.padding(4.dp),
                roundedCornerShapeDp = 6,
                tagName = winType.tagName,
                backgroundColor = winType.backgroundColor,
                textColor = winType.textColor
            )
        }
        Spacer(modifier = Modifier.weight(1F))

        if (score != null) {
            val winTextColor = if (isWin == true) {
                KBongGrayscaleGray8
            } else {
                KBongGrayscaleGray4
            }
            Text(
                text = "$score",
                style = KBongTypography.Heading2,
                color = winTextColor
            )
        }


    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewGameScoreBodyContent() {
    GameScoreBodyContent(
        teamName = "SSG",
        isMyTeam = true,
        isWin = true,
        score = 2
    )
}