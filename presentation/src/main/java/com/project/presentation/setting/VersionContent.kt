package com.project.presentation.setting

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.project.kbong.designsystem.theme.KBongGrayscaleGray5
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R

@Composable
fun VersionContent(
    modifier: Modifier = Modifier,
    versionCode: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "버전 정보 $versionCode",
            style = KBongTypography.Heading2Medium,
            color = KBongGrayscaleGray8
        )
        Spacer(modifier = Modifier.weight(1F))
        Text(
            text = stringResource(R.string.latest_version),
            style = KBongTypography.Heading2Medium,
            color = KBongGrayscaleGray5
        )
    }
}