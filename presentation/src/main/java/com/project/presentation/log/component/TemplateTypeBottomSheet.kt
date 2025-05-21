package com.project.presentation.log.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongGrayscaleGray1
import com.project.kbong.designsystem.theme.KBongGrayscaleGray6
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongGrayscaleGray9
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.presentation.R
import com.project.presentation.log.LogInputType
import com.project.kbong.designsystem.theme.KBongTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplateTypeBottomSheet(
    selectedType: LogInputType,
    onSelect: (LogInputType) -> Unit,
    onDismiss: () -> Unit
) {
    val iconMapSelected = mapOf(
        LogInputType.TEXT to R.drawable.select_free,
        LogInputType.OBJECTIVE to R.drawable.select_objective,
        LogInputType.SUBJECTIVE to R.drawable.select_subjective
    )
    val iconMapUnselected = mapOf(
        LogInputType.TEXT to R.drawable.unselect_free,
        LogInputType.OBJECTIVE to R.drawable.unselect_objective,
        LogInputType.SUBJECTIVE to R.drawable.unselect_subjective
    )

    var tempSelectedType by remember { mutableStateOf(selectedType) }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    // 처음 보여질 때 바로 확장
    LaunchedEffect(Unit) {
        sheetState.show()
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        windowInsets = WindowInsets(0),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        dragHandle = null,
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            // 상단 제목
            Text(
                text = "어떤 형식으로 기록할까요?",
                style = KBongTypography.Heading1,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // 템플릿 카드들
            LogInputType.entries.forEachIndexed { index, type ->
                val isSelected = type == tempSelectedType
                val backgroundColor = KBongGrayscaleGray1
                val borderColor = if (isSelected) KBongPrimary else Color.Transparent
                val iconRes = if (isSelected) iconMapSelected[type]!! else iconMapUnselected[type]!!

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp) // 고정 높이
                        .clip(RoundedCornerShape(12.dp))
                        .background(backgroundColor)
                        .border(
                            width = if (isSelected) 1.6.dp else 0.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { tempSelectedType = type }
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(58.dp)
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Column {
                        Text(
                            text = type.title,
                            style = KBongTypography.Heading2,
                            color = KBongGrayscaleGray9
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = type.description,
                            style = KBongTypography.Label1Normal,
                            color = KBongGrayscaleGray6
                        )
                    }
                }

                if (index < LogInputType.entries.size - 1) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            // 완료 버튼
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    onSelect(tempSelectedType)
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = KBongPrimary)
            ) {
                Text("완료", color = Color.White, style = KBongTypography.Body1Normal)
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTemplateTypeBottomSheet() {
    Surface {
        Box(modifier = Modifier.fillMaxSize()) {
            // 실제 BottomSheet처럼 보이진 않지만 내부 구성 확인용
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(Color.LightGray)
            ) {
                TemplateTypeBottomSheet(
                    selectedType = LogInputType.SUBJECTIVE,
                    onSelect = {},
                    onDismiss = {}
                )
            }
        }
    }
}