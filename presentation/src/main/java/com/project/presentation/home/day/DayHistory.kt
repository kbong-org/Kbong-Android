package com.project.presentation.home.day

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongGrayscaleGray7
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R

@Composable
fun DayHistory(
    awayTeam: String,
    homeTeam: String,
    stadium: String,
    type: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = awayTeam,
                    style = KBongTypography.Heading2,
                    color = KBongGrayscaleGray8
                )
                Text(
                    text = stringResource(R.string.vs),
                    style = KBongTypography.Heading2,
                    color = KBongPrimary
                )
                Text(
                    text = homeTeam,
                    style = KBongTypography.Heading2,
                    color = KBongGrayscaleGray8
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.location),
                    contentDescription = "location"
                )
                Text(
                    text = stadium,
                    style = KBongTypography.Caption1.copy(fontWeight = FontWeight.Medium),
                    color = KBongGrayscaleGray7
                )
            }

        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewDayHistory() {
    DayHistory(
        awayTeam = "KT",
        homeTeam = "삼성",
        stadium = "수원 KT위즈파크",
        type = "FREE"
    )
}