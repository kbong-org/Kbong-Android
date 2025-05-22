package com.project.presentation.log.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.domain.model.log.Emotion
import com.project.domain.model.log.GameInfo
import com.project.kbong.designsystem.theme.KBongGrayscaleGray1
import com.project.kbong.designsystem.theme.KBongGrayscaleGray4
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R

@DrawableRes
fun Emotion.getEmojiRes(): Int {
    return when (this) {
        Emotion.HAPPY -> com.project.kbong.designsystem.R.drawable.win
        Emotion.SAD -> com.project.kbong.designsystem.R.drawable.lose
        Emotion.NORMAL -> com.project.kbong.designsystem.R.drawable.cloud
        Emotion.ANGRY -> com.project.kbong.designsystem.R.drawable.lightning
    }
}

@Composable
fun LogDetailHeader(gameInfo: GameInfo, emotion: Emotion) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        // 구단 vs 구단 텍스트 단독 Row
        Row {
            Spacer(modifier = Modifier.width(24.dp))

            Text(
                text = "${gameInfo.awayTeamDisplayName} ",
                style = KBongTypography.Title
            )
            Text(
                text = "vs",
                style = KBongTypography.Title.copy(color = KBongPrimary)
            )
            Text(
                text = " ${gameInfo.homeTeamDisplayName}",
                style = KBongTypography.Title
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // 이모지 + 날짜/장소 Row
        Row(verticalAlignment = Alignment.Top) {

            Spacer(modifier = Modifier.width(18.dp))

            // 이모지
            Image(
                painter = painterResource(id = emotion.getEmojiRes()),
                contentDescription = null,
                modifier = Modifier
                    .width(42.dp)
                    .height(42.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // 날짜 & 장소 텍스트
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(18.dp)
                            .height(18.dp)
                            .wrapContentSize(align = Alignment.Center)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.clock),
                            contentDescription = "시간"
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = gameInfo.date,
                        style = KBongTypography.Label2Medium,
                        color = KBongGrayscaleGray4
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(18.dp)
                            .height(18.dp)
                            .wrapContentSize(align = Alignment.Center)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "장소",
                            modifier = Modifier.width(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = gameInfo.stadiumFullName,
                        style = KBongTypography.Label2Medium,
                        color = KBongGrayscaleGray4
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(34.dp))
        Divider(thickness = 6.dp, color = KBongGrayscaleGray1)
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun LogDetailHeaderPreview() {
    LogDetailHeader(
        gameInfo = GameInfo(
            id = 1,
            awayTeamDisplayName = "KT",
            homeTeamDisplayName = "한화",
            date = "3월 23일 토요일",
            stadiumFullName = "수원 KT위즈파크"
        ),
        emotion = Emotion.HAPPY
    )
}