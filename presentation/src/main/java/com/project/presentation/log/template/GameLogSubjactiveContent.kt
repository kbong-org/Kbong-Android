package com.project.presentation.log.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.project.kbong.designsystem.theme.KBongGrayscaleGray1
import com.project.kbong.designsystem.theme.KBongGrayscaleGray4
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R

@Composable
fun GameLogSubjectiveContent(
    imageUris: List<Uri>,
    onAddImage: () -> Unit,
    onDeleteImage: (Int) -> Unit,
    canAdd: Boolean,
    text: String,
    onTextChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            "오늘 경기를 보면서 '야, 야구장에서만 느낄 수 있는 기분이다!' 했던 순간이 있다면?",
            style = KBongTypography.Heading2
        )
        Spacer(modifier = Modifier.height(24.dp))

        Divider(
            thickness = 2.dp,
            color = KBongGrayscaleGray1,
        )

        Spacer(modifier = Modifier.height(24.dp))

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
                        Icon(
                            painter = rememberAsyncImagePainter(R.drawable.photo_delete),
                            contentDescription = "삭제",
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(20.dp)
                                .clickable { onDeleteImage(index) },
                            tint = Color.Gray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (imageUris.isEmpty() && canAdd) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF5F5F5))
                    .clickable { onAddImage() },
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "사진 추가", tint = Color.Gray)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        Box {
            BasicTextField(
                value = text,
                onValueChange = {
                    if (it.length <= 100) onTextChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                textStyle = KBongTypography.Body2Normal.copy(color = Color.Black)
            )

            if (text.isEmpty()) {
                Text(
                    text = "질문에 답하기",
                    style = KBongTypography.Body2Normal,
                    color = KBongGrayscaleGray4,
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (text.isNotBlank()) {
            Text(
                text = "${text.length}/100",
                style = KBongTypography.Label2Medium,
                color = KBongGrayscaleGray4,
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}