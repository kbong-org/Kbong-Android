package com.project.presentation.setting.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.*

@Composable
fun LogoutDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    buttonColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x99000000)) // 배경
            .clickable(enabled = false) {}
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            Text(
                text = "로그아웃 하시겠어요?",
                style = KBongTypography.Heading2,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(KBongGrayscaleGray2)
                        .clickable { onDismiss() }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "취소",
                        style = KBongTypography.Body1Normal,
                        color = KBongGrayscaleGray8
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(buttonColor) // 팀 컬러 적용
                        .clickable { onConfirm() }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "로그아웃",
                        style = KBongTypography.Body1Normal,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLogoutDialog() {
    LogoutDialog(
        onConfirm = {},
        onDismiss = {},
        buttonColor = KBongStatusDestructive
    )
}