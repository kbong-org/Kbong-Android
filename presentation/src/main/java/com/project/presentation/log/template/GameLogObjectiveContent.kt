package com.project.presentation.log.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.project.domain.model.question.ChoiceQuestion
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R

@Composable
fun GameLogObjectiveContent(
    imageUris: List<Uri>,
    onAddImage: () -> Unit,
    onDeleteImage: (Int) -> Unit,
    canAdd: Boolean,
    selectedOption: String?,
    onSelectOption: (String) -> Unit,
    question: ChoiceQuestion?,
    currentPage: Int,
    totalPages: Int = 3,
    onPageChange: (Int) -> Unit = {},
    onRefreshQuestion: () -> Unit
) {
    val options = question?.answerOptions?.map { it.text } ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF9F9FA), RoundedCornerShape(20.dp))
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        if (imageUris.isNotEmpty()) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
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
            Spacer(modifier = Modifier.height(24.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("$currentPage/$totalPages", style = KBongTypography.Label2Medium, color = Color.Gray)
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "질문 바꾸기",
                style = KBongTypography.Label2Medium,
                color = Color(0xFF6B7AFF),
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFEFF2FF))
                    .clickable { onRefreshQuestion() }
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(question?.questionText ?: "전체적인 경기 직관 소감은 어떤가요?", style = KBongTypography.Heading2)

        Spacer(modifier = Modifier.height(16.dp))

        options.forEach { option ->
            val isSelected = selectedOption == option
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) Color(0xFFEFF2FF) else Color.White)
                    .border(
                        width = 1.dp,
                        color = if (isSelected) Color(0xFF6B7AFF) else Color(0xFFE0E0E0),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { onSelectOption(option) }
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(option, style = KBongTypography.Body2Reading, modifier = Modifier.weight(1f))
                if (isSelected) {
                    Image(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = "선택됨",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val canGoBack = currentPage > 1
            val canGoNext = currentPage < totalPages

            Image(
                painter = painterResource(
                    id = if (canGoBack) R.drawable.select_before else R.drawable.unselect_before
                ),
                contentDescription = "이전",
                modifier = Modifier
                    .size(32.dp)
                    .clickable(enabled = canGoBack) {
                        onPageChange(currentPage - 1)
                    }
            )

            Image(
                painter = painterResource(
                    id = if (canGoNext) R.drawable.select_after else R.drawable.unselect_after
                ),
                contentDescription = "다음",
                modifier = Modifier
                    .size(32.dp)
                    .clickable(enabled = canGoNext) {
                        onPageChange(currentPage + 1)
                    }
            )
        }
    }
}