package com.project.kbong.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongGrayscaleGray0
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongGrayscaleGray5
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongTypography

@Composable
fun KBongLargeButton(
    modifier: Modifier = Modifier,
    title: String = "",
    shape: Shape = RoundedCornerShape(14.dp),
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(vertical = 14.dp),
    onClick: () -> Unit = {}
) {

    Button(
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
        shape = shape,
        colors = ButtonColors(
            containerColor = KBongPrimary,
            contentColor = KBongGrayscaleGray0,
            disabledContainerColor = KBongGrayscaleGray2,
            disabledContentColor = KBongGrayscaleGray5
        ),
        onClick = onClick
    ) {
        Text(
            text = title,
            style = KBongTypography.Body1Normal,
        )
    }
}

@Preview
@Composable
private fun PreviewKBongLargeEnabledButton() {
    KBongLargeButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        title = "저장",
        enabled = true,
        onClick = {}
    )
}

@Preview
@Composable
private fun PreviewKBongLargeDiSabledButton() {
    KBongLargeButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        title = "저장",
        enabled = false,
        onClick = {}
    )
}