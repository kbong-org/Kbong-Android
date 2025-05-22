package com.project.presentation.log.detail.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.project.kbong.designsystem.theme.KBongGrayscaleGray1
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongTeamBears
import com.project.kbong.designsystem.theme.KBongTeamBears10
import com.project.kbong.designsystem.theme.KBongTeamEagles
import com.project.kbong.designsystem.theme.KBongTeamEagles10
import com.project.kbong.designsystem.theme.KBongTeamGiants
import com.project.kbong.designsystem.theme.KBongTeamGiants10
import com.project.kbong.designsystem.theme.KBongTeamHeroes
import com.project.kbong.designsystem.theme.KBongTeamHeroes10
import com.project.kbong.designsystem.theme.KBongTeamKTSub
import com.project.kbong.designsystem.theme.KBongTeamKTSub10
import com.project.kbong.designsystem.theme.KBongTeamLions
import com.project.kbong.designsystem.theme.KBongTeamLions10
import com.project.kbong.designsystem.theme.KBongTeamSsg
import com.project.kbong.designsystem.theme.KBongTeamSsg10
import com.project.kbong.designsystem.theme.KBongTeamTigers
import com.project.kbong.designsystem.theme.KBongTeamTigers10
import com.project.kbong.designsystem.theme.KBongTeamTwins
import com.project.kbong.designsystem.theme.KBongTeamTwins10
import com.project.kbong.designsystem.theme.KBongTypography

@Composable
fun LogDetailObjectiveContent(
    log: DailyLogDetail,
    myTeamColor: Color? = null
) {
    val primaryColor = myTeamColor ?: KBongPrimary
    val backgroundColor = when (primaryColor) {
        KBongTeamBears -> KBongTeamBears10
        KBongTeamGiants -> KBongTeamGiants10
        KBongTeamLions -> KBongTeamLions10
        KBongTeamEagles -> KBongTeamEagles10
        KBongTeamTigers -> KBongTeamTigers10
        KBongTeamTwins -> KBongTeamTwins10
        KBongTeamSsg -> KBongTeamSsg10
        KBongTeamHeroes -> KBongTeamHeroes10
        KBongTeamKTSub -> KBongTeamKTSub10
        else -> KBongGrayscaleGray1
    }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        if (log.imagePaths.isNotEmpty()) {
            ImagePager(log.imagePaths)
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        log.detail?.answers?.forEachIndexed { index, answer ->
            // Q번호 + 질문 분리
            Row {
                Text(
                    text = "Q${index + 1}. ",
                    modifier = Modifier.width(32.dp),
                    style = KBongTypography.Heading2.copy(
                        color = primaryColor,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = answer.questionText,
                    style = KBongTypography.Heading2.copy(
                        color = KBongGrayscaleGray8,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .padding(start = 32.dp)
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = answer.answerOptionText,
                    style = KBongTypography.Caption2SemiBold,
                    color = primaryColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun LogDetailObjectiveContentPreview() {
    LogDetailObjectiveContent(
        log = DailyLogDetail(
            id = 55,
            type = "CHOICE",
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
                text = null,
                questionId = null,
                questionText = null,
                answer = null,
                selectedOption = null,
                answers = listOf(
                    Detail.Answer(
                        questionId = 1,
                        questionText = "오늘 경기는 어땠나요?",
                        answerOptionId = 1,
                        answerOptionText = "만족스러웠어요"
                    ),
                    Detail.Answer(
                        questionId = 2,
                        questionText = "응원하는 팀이 이겼나요?",
                        answerOptionId = 2,
                        answerOptionText = "네! 너무 좋았어요"
                    ),
                    Detail.Answer(
                        questionId = 3,
                        questionText = "기억에 남는 장면은?",
                        answerOptionId = 3,
                        answerOptionText = "9회말 투아웃 만루에서 끝내기 홈런!"
                    )
                )
            )
        ),
        myTeamColor = KBongTeamTwins
    )
}