package com.project.kbong.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongGrayscaleGray0
import com.project.kbong.designsystem.theme.KBongGrayscaleGray7
import com.project.kbong.designsystem.theme.KBongTypography.Body1Normal

@Composable
fun KBongSnackbar(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(KBongGrayscaleGray7)

    ) {
        Text(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 14.dp),
            text = text,
            style = Body1Normal,
            color = KBongGrayscaleGray0
        )
    }
}

@Preview
@Composable
private fun PreviewKBongSnackbar() {
    KBongSnackbar(
        modifier = Modifier.padding(15.dp),
        text = "Snackbar test"
    )
}