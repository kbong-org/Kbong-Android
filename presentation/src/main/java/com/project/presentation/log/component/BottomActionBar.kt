package com.project.presentation.log.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongGrayscaleGray1
import com.project.presentation.R

@Composable
fun BottomActionBar(
    onPhotoClick: () -> Unit,
    onTemplateClick: () -> Unit,
    isPhotoAddEnabled: Boolean
) {
    Column {
        Divider(
            thickness = 2.dp,
            color = KBongGrayscaleGray1,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 사진 추가 버튼
            Image(
                painter = painterResource(id = R.drawable.add_photo),
                contentDescription = "사진 추가",
                modifier = Modifier
                    .clickable(enabled = isPhotoAddEnabled) { onPhotoClick() }
                    .size(24.dp)
            )

            // 템플릿 변경 버튼
            Image(
                painter = painterResource(id = R.drawable.change_template),
                contentDescription = "템플릿 변경",
                modifier = Modifier
                    .clickable { onTemplateClick() }
                    .size(24.dp)
            )
        }
    }
}
