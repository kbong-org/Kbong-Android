package com.project.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongTeamBears
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R

@Composable
fun DateTopContent(
    currentMonth: String,
    myTeam: String,
    teamColor: Color,
    onClickMonth: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 18.dp, bottom = 14.dp)
                .clickable { onClickMonth() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${currentMonth}월",
                style = KBongTypography.Heading1,
                color = KBongGrayscaleGray8
            )
            Image(
                painter = painterResource(R.drawable.month_arrow),
                contentDescription = "monthArrow",
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 18.dp, bottom = 14.dp, end = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterEnd)
                .background(teamColor)
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 14.dp),
                text = myTeam,
                color = Color.White,
                style = KBongTypography.Caption1,
            )
        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewDateTopContent() {
    DateTopContent(
        currentMonth = "3",
        onClickMonth = {},
        myTeam = "두산 베어스",
        teamColor = KBongTeamBears
    )
}