package com.project.presentation.log.detail.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.domain.model.log.DailyLogDetail
import com.project.domain.model.log.Detail
import com.project.domain.model.log.GameInfo
import com.project.kbong.designsystem.theme.KBongTypography

@Composable
fun LogDetailFreeContent(log: DailyLogDetail) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        if (log.imagePaths.isNotEmpty()) {
            ImagePager(log.imagePaths)
            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(
            text = log.detail?.text ?: "내용 없음",
            style = KBongTypography.Body1Normal
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun LogDetailFreeContentPreview() {
    LogDetailFreeContent(
        log = DailyLogDetail(
            id = 1,
            type = "FREE",
            emotion = "HAPPY",
            imagePaths = listOf(
                "https://example.com/image1.jpg",
                "https://example.com/image2.jpg"
            ),
            gameInfo = GameInfo(
                id = 1,
                awayTeamDisplayName = "한화",
                homeTeamDisplayName = "NC",
                stadiumFullName = "창원",
                date = "2025년 3월 23일 토요일"
            ),
            detail = Detail(
                text = "오늘 경기는 정말 인상 깊었고, 팀워크가 돋보였어요!",
                questionId = null,
                questionText = null,
                answer = null,
                selectedOption = null,
                answers = null
            )
        )
    )
}
