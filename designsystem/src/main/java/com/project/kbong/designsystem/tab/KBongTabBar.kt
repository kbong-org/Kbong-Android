package com.project.kbong.designsystem.tab

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongGrayscaleGray3
import com.project.kbong.designsystem.theme.KBongGrayscaleGray5
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongTypography


@Composable
fun KBongTabBar(
    modifier: Modifier = Modifier,
    selectedTitle: String,
    titleList: List<String>,
    onClickItem: (String) -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        HorizontalDivider(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 0.5.dp),
            thickness = 1.dp,
            color = KBongGrayscaleGray3
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp)
        ) {
            titleList.forEach { title ->
                TabItem(
                    modifier = Modifier.weight((titleList.size / 1).toFloat()),
                    title = title,
                    isSelected = title == selectedTitle,
                    onClickItem = { onClickItem(title) }
                )
            }
        }
    }
}

@Composable
private fun TabItem(
    modifier: Modifier = Modifier,
    title: String,
    isSelected: Boolean,
    onClickItem: () -> Unit
) {
    Column(
        modifier = modifier.clickable { onClickItem() }
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            textAlign = TextAlign.Center,
            style = if (isSelected) {
                KBongTypography.Heading2
            } else {
                KBongTypography.Heading2.copy(fontWeight = FontWeight.Medium)
            },
            color = if (isSelected) {
                KBongGrayscaleGray8
            } else {
                KBongGrayscaleGray5
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        if (isSelected) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(2.dp)),
                thickness = 2.dp,
                color = KBongGrayscaleGray8
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewKBongBaseTabBar() {
    KBongTabBar(
        modifier = Modifier.padding(10.dp),
        selectedTitle = "직관기록",
        titleList = listOf("직관기록", "경기일정"),
        onClickItem = {},

        )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewTabItem() {
    TabItem(
        modifier = Modifier.padding(20.dp),
        title = "직관기록",
        isSelected = true,
        onClickItem = {}
    )
}
