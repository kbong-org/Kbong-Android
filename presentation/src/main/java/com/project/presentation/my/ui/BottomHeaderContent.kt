package com.project.presentation.my.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongGrayscaleGray3
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongGrayscaleGray9
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R

@Composable
fun BottomHeaderContent(
    isCatalogSelect: Boolean = false,
    isListSelect: Boolean = false,
    onClickViewType: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.game_history),
            style = KBongTypography.Heading2,
            color = KBongGrayscaleGray9
        )
        Spacer(modifier = Modifier.weight(1F))
        Image(
            modifier = Modifier.clickable {
                onClickViewType(CATALOG)
            },
            colorFilter = ColorFilter.tint(
                color = if (isCatalogSelect) {
                    KBongGrayscaleGray8
                } else {
                    KBongGrayscaleGray3
                }
            ),
            painter = painterResource(R.drawable.array1_disable),
            contentDescription = "array1"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            modifier = Modifier.clickable {
                onClickViewType(LIST)
            },
            colorFilter = ColorFilter.tint(
                if (isListSelect) {
                    KBongGrayscaleGray8
                } else {
                    KBongGrayscaleGray3
                }
            ),
            painter = painterResource(R.drawable.array2_disable),
            contentDescription = "array2"
        )
    }
}

@Preview
@Composable
private fun PreviewBottomHeaderContent() {
    BottomHeaderContent(
        onClickViewType = {}
    )
}