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
import androidx.compose.ui.unit.dp
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

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = Modifier.navigationBarsPadding(),
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
            Text(
                text = "어떤 형식으로 기록할까요?",
                style = KBongTypography.Heading2
            )

            Spacer(modifier = Modifier.height(20.dp))

            LogInputType.entries.forEach { type ->
                val isSelected = type == tempSelectedType
                val backgroundColor = if (isSelected) Color(0xFFF3F6FF) else Color(0xFFF9F9FA)
                val borderColor = if (isSelected) Color(0xFF3D5AFE) else Color.Transparent
                val iconRes = if (isSelected) iconMapSelected[type]!! else iconMapUnselected[type]!!

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(backgroundColor)
                        .border(
                            width = if (isSelected) 1.dp else 0.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { tempSelectedType = type }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = type.title,
                            style = KBongTypography.Body1NormalSemiBold,
                            color = Color.Black
                        )
                        Text(
                            text = type.description,
                            style = KBongTypography.Label2Medium,
                            color = Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    onSelect(tempSelectedType)
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D5AFE))
            ) {
                Text("완료", color = Color.White)
            }
        }
    }
}
