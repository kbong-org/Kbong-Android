package com.project.kbong.designsystem.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongPrimary10
import com.project.kbong.designsystem.theme.KBongTypography

@Composable
fun KBongTag(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
    roundedCornerShapeDp: Int = 8,
    tagName: String,
    backgroundColor: Color,
    textColor: Color
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(roundedCornerShapeDp.dp))
            .background(backgroundColor)
    ) {
        Text(
            modifier = textModifier,
            text = tagName,
            color = textColor,
            style = KBongTypography.Caption2.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewKBongTag() {
    KBongTag(
        modifier = Modifier.padding(15.dp),
        tagName = "태그",
        backgroundColor = KBongPrimary10,
        textColor = KBongPrimary
    )
}