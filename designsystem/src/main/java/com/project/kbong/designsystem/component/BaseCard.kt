package com.project.kbong.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.R
import com.project.kbong.designsystem.theme.KBongGrayscaleGray10
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongPrimary10
import com.project.kbong.designsystem.theme.KBongTypography

@Composable
fun KbongArticleCard(
    title: String,
    tag: String,
    thumbnail: Painter,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(135.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(KBongPrimary10)
            .clickable { onClick() }
            .padding(vertical = 16.dp, horizontal = 20.dp) // 마진 적용
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp) // 타이틀과 태그 간격 조정
            ) {
                Text(
                    text = title,
                    style = KBongTypography.Heading2,
                    color = KBongGrayscaleGray10
                )

                KBongTag(text = tag, KBongPrimary10, KBongPrimary, modifier = Modifier)
            }

            Image(
                painter = thumbnail,
                contentDescription = "Thumbnail",
                modifier = Modifier
                    .width(136.dp)
                    .height(102.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(KBongGrayscaleGray2)
            )
        }
    }
}

@Preview
@Composable
fun BaseCardPreview() {
    KbongArticleCard(
        title = "잠실 야구장 니즈 별 꿀좌석은?✨",
        tag = "경기장",
        thumbnail = painterResource(id = R.drawable.img_sample_article_card), // 이미지 리소스
        onClick = {}
    )
}