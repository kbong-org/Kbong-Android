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
import com.project.kbong.designsystem.theme.KBongGrayscaleGray4
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R

@Composable
fun GameLogTextContent(
    imageUris: List<Uri>,
    onAddImage: () -> Unit,
    onDeleteImage: (Int) -> Unit,
    canAdd: Boolean,
    text: String,
    onTextChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
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
                            painter = androidx.compose.ui.res.painterResource(id = R.drawable.photo_delete),
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
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF5F5F5))
                    .clickable { onAddImage() }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Add, contentDescription = "사진 추가", tint = Color.Gray)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        Box {
            BasicTextField(
                value = text,
                onValueChange = {
                    if (it.length <= 300) onTextChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                textStyle = KBongTypography.Body1NormalSemiBold.copy(color = Color.Black)
            )

            if (text.isEmpty()) {
                Text(
                    text = "오늘 경기에 대해 자유롭게 작성해보세요!",
                    style = KBongTypography.Body1NormalSemiBold,
                    color = KBongGrayscaleGray4,
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (text.isNotBlank()) {
            Text(
                text = "${text.length}/300",
                style = KBongTypography.Label2Medium,
                color = KBongGrayscaleGray4,
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}
