package com.project.presentation.log.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.project.domain.model.question.AnswerOption
import com.project.domain.model.question.ChoiceQuestion
import com.project.kbong.designsystem.theme.KBongAccentButtonBlue
import com.project.kbong.designsystem.theme.KBongGrayscaleGray1
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongGrayscaleGray3
import com.project.kbong.designsystem.theme.KBongGrayscaleGray4
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongPrimary10
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R

@Composable
fun GameLogObjectiveContent(
    imageUris: List<Uri>,
    onAddImage: () -> Unit,
    onDeleteImage: (Int) -> Unit,
    canAdd: Boolean,
    teamColor: Color,
    teamBgColor: Color,
    selectedOption: String?,
    onSelectOption: (String) -> Unit,
    question: ChoiceQuestion?,
    currentPage: Int,
    totalPages: Int = 3,
    onPageChange: (Int) -> Unit = {},
    onRefreshQuestion: () -> Unit
) {
    val options = question?.answerOptions?.map { it.text } ?: emptyList()

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        // 이미지 프리뷰 영역 (카드 바깥)
        if (imageUris.isNotEmpty()) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                items(imageUris.size) { index ->
                    Box {
                        Image(
                            painter = rememberAsyncImagePainter(imageUris[index]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Image(
                            painter = painterResource(id = R.drawable.photo_delete),
                            contentDescription = "삭제",
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(20.dp)
                                .clickable { onDeleteImage(index) }
                        )
                    }
                }
            }
        }

        // 내부 카드형 컨텐츠
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(KBongGrayscaleGray1, RoundedCornerShape(20.dp))
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            // 질문 인덱스 & 질문 바꾸기
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "$currentPage/$totalPages",
                    style = KBongTypography.Title,
                    color = KBongGrayscaleGray4
                )
                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(teamBgColor)
                        .clickable { onRefreshQuestion() }
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.refresh),
                        contentDescription = "질문 바꾸기 아이콘",
                        modifier = Modifier.size(14.dp),
                        tint = teamColor
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "질문 바꾸기",
                        style = KBongTypography.Label2Medium,
                        color = teamColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                question?.questionText ?: "전체적인 경기 직관 소감은 어떤가요?",
                style = KBongTypography.Heading1
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 선택 옵션
            options.forEach { option ->
                val isSelected = selectedOption == option
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(KBongGrayscaleGray2)
                        .clickable { onSelectOption(option) }
                        .padding(horizontal = 16.dp, vertical = 18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = option,
                        style = KBongTypography.Body2Normal,
                        modifier = Modifier.weight(1f),
                        color = KBongGrayscaleGray8
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Image(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = "체크 아이콘",
                        modifier = Modifier.size(20.dp),
                        colorFilter = ColorFilter.tint(
                            if (isSelected) teamColor
                            else KBongGrayscaleGray3
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 이전 / 다음 버튼
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val canGoBack = currentPage > 1
                val canGoNext = currentPage < totalPages

                Image(
                    painter = painterResource(
                        id = if (canGoBack) R.drawable.select_before else R.drawable.unselect_before
                    ),
                    contentDescription = "이전",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable(enabled = canGoBack) {
                            onPageChange(currentPage - 1)
                        }
                )

                Spacer(modifier = Modifier.width(32.dp))

                Image(
                    painter = painterResource(
                        id = if (canGoNext) R.drawable.select_after else R.drawable.unselect_after
                    ),
                    contentDescription = "다음",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable(enabled = canGoNext) {
                            onPageChange(currentPage + 1)
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameLogObjectiveContent() {
    val fakeQuestion = ChoiceQuestion(
        questionId = 1,
        questionText = "오늘 경기에서 가장 인상깊었던 점은?",
        answerOptions = listOf(
            AnswerOption(1, "타자의 활약"),
            AnswerOption(2, "투수의 투구"),
            AnswerOption(3, "응원 열기"),
            AnswerOption(4, "날씨와 분위기")
        )
    )

    GameLogObjectiveContent(
        imageUris = emptyList(),
        onAddImage = {},
        onDeleteImage = {},
        canAdd = true,
        selectedOption = "응원 열기",
        onSelectOption = {},
        question = fakeQuestion,
        currentPage = 1,
        totalPages = 3,
        onPageChange = {},
        onRefreshQuestion = {},
        teamColor = KBongPrimary,
        teamBgColor = KBongPrimary10
    )
}