package com.project.presentation.log.detail.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.domain.model.log.DailyLogDetail
import com.project.domain.model.log.Detail
import com.project.domain.model.log.GameInfo
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongTeamTwins
import com.project.kbong.designsystem.theme.KBongTypography

@Composable
fun LogDetailSubjectiveContent(
    log: DailyLogDetail,
    myTeamColor: Color? = null
) {
    val primaryColor = myTeamColor ?: KBongPrimary

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        if (log.imagePaths.isNotEmpty()) {
            ImagePager(log.imagePaths)
            Spacer(modifier = Modifier.height(16.dp))
        }

        log.detail?.let { detail ->
            Row {
                Text(
                    text = "Q.",
                    style = KBongTypography.Heading2.copy(
                        color = primaryColor,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = detail.questionText ?: "질문 없음",
                    style = KBongTypography.Heading2.copy(
                        color = com.project.kbong.designsystem.theme.KBongGrayscaleGray8,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Divider(
                thickness = 1.dp,
                color = com.project.kbong.designsystem.theme.KBongGrayscaleGray2
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = detail.text ?: "답변 없음",
                style = KBongTypography.Body1Reading,
                color = com.project.kbong.designsystem.theme.KBongGrayscaleGray8
            )

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun LogDetailSubjectiveContentPreview() {
    LogDetailSubjectiveContent(
        log = DailyLogDetail(
            id = 55,
            type = "SHORT",
            emotion = "NORMAL",
            imagePaths = listOf(
                "https://example.com/image1.jpg",
                "https://example.com/image2.jpg"
            ),
            gameInfo = GameInfo(
                id = 1172,
                awayTeamDisplayName = "한화",
                homeTeamDisplayName = "NC",
                stadiumFullName = "창원",
                date = "2025-05-21"
            ),
            detail = Detail(
                text = "9회말 투아웃 만루에서 끝내기 안타!",
                questionId = 1,
                questionText = "오늘 경기에서 승리에 가장 결정적이었던 장면은?",
                answer = "9회말 투아웃 만루에서 끝내기 안타!",
                selectedOption = null,
                answers = null
            )
        ),
        myTeamColor = KBongTeamTwins
    )
}
