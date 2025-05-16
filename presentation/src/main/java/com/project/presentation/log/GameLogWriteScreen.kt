package com.project.presentation.log

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.domain.model.day.DailyGameLog
import com.project.kbong.designsystem.navigationbar.KBongTopBar
import com.project.kbong.designsystem.theme.KBongGrayscaleGray5
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

enum class LogInputType(val title: String, val description: String) {
    TEXT("자율형", "정해진 양식 없이 자유롭게 기록해요"),
    OBJECTIVE("객관형", "체크 형식으로 간단히 기록해요"),
    SUBJECTIVE("주관형", "간단한 한줄 작성으로 기록해요")
}

@Composable
fun GameLogWriteRoute(
    navController: NavController,
    gameInfo: DailyGameLog,
    selectedDate: LocalDate,
    onSubmit: () -> Unit = {}
) {
    var inputType by remember { mutableStateOf(LogInputType.TEXT) }
    var selectedEmotion by remember { mutableStateOf(com.project.kbong.designsystem.R.drawable.emotion) }
    var isTemplateSheetVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        KBongTopBar(
            isBackButton = true,
            onClickBackButton = { navController.popBackStack() },
            leftContent = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = gameInfo.awayTeamDisplayName, style = KBongTypography.Heading2)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "vs", style = KBongTypography.Heading2, color = Color(0xFF3D5AFE))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = gameInfo.homeTeamDisplayName, style = KBongTypography.Heading2)
                }
            },
            rightContent = {
                Text(
                    text = "글 올리기",
                    color = KBongGrayscaleGray5,
                    modifier = Modifier.clickable { onSubmit() },
                    style = KBongTypography.Body1NormalSemiBold
                )
            }
        )

        HeaderGameInfo(
            game = gameInfo,
            selectedDate = selectedDate,
            selectedEmotion = selectedEmotion,
            onEmotionSelected = { selectedEmotion = it },
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (inputType) {
            LogInputType.TEXT -> GameLogTextContent()
            LogInputType.OBJECTIVE -> GameLogObjectiveContent()
            LogInputType.SUBJECTIVE -> GameLogSubjectiveContent()
        }

        Spacer(modifier = Modifier.weight(1f))

        if (isTemplateSheetVisible) {
            TemplateTypeBottomSheet(
                selectedType = inputType,
                onSelect = {
                    inputType = it
                    isTemplateSheetVisible = false
                },
                onDismiss = { isTemplateSheetVisible = false }
            )
        }

        // GameLogWriteRoute의 마지막 부분 수정
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconToggleButton(
                checked = false,
                onCheckedChange = { /* 왼쪽 버튼 동작 (예: 텍스트/이미지 전환 등) */ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.tip_off),
                    contentDescription = null,
                    tint = Color.Gray
                )
            }

            Text(
                text = "질문 바꾸기",
                color = Color(0xFF3D5AFE),
                style = KBongTypography.Label2Medium,
                modifier = Modifier
                    .clickable { isTemplateSheetVisible = true }
                    .align(Alignment.CenterVertically)
            )
        }

        if (isTemplateSheetVisible) {
            TemplateTypeBottomSheet(
                selectedType = inputType,
                onSelect = {
                    inputType = it
                    isTemplateSheetVisible = false
                },
                onDismiss = { isTemplateSheetVisible = false }
            )
        }
    }
}

@Composable
fun HeaderGameInfo(
    game: DailyGameLog,
    selectedDate: LocalDate,
    selectedEmotion: Int,
    onEmotionSelected: (Int) -> Unit,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.Top
    ) {
        // 감정 아이콘 + 팝업 Anchor
        Box {
            Image(
                painter = painterResource(id = selectedEmotion),
                contentDescription = null,
                modifier = Modifier
                    .size(42.dp)
                    .clickable { isMenuExpanded = true }
            )

            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false },
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    listOf(
                        com.project.kbong.designsystem.R.drawable.win,
                        com.project.kbong.designsystem.R.drawable.cloud,
                        com.project.kbong.designsystem.R.drawable.lose,
                        com.project.kbong.designsystem.R.drawable.lightning
                    ).forEach { resId ->
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = null,
                            modifier = Modifier
                                .size(42.dp)
                                .clickable {
                                    onEmotionSelected(resId)
                                    isMenuExpanded = false
                                }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = null,
                    tint = KBongGrayscaleGray5,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))

                val dateText = "${selectedDate.monthValue}월 ${selectedDate.dayOfMonth}일 ${selectedDate.dayOfWeek.getDisplayName(
                    TextStyle.FULL, Locale.KOREAN)}"

                Text(
                    text = dateText,
                    style = KBongTypography.Label2Medium,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = null,
                    tint = KBongGrayscaleGray5,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = game.stadiumDisplayName,
                    style = KBongTypography.Label2Medium,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

    }
}

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
        modifier = Modifier.navigationBarsPadding(), // 시스템 바 위까지 올라오게
        windowInsets = WindowInsets(0), // 시스템 inset 무시해서 전체 시트로
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        dragHandle = null, // 핸들 없애서 겹쳐 보이는 현상 제거
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

@Composable
fun GameLogTextContent() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Spacer(modifier = Modifier.height(12.dp))
        Text("오늘 경기에 대해 자유롭게 작성해보세요!", color = Color.Gray, style = KBongTypography.Body2Reading)
    }
}

@Composable
fun GameLogObjectiveContent() {
    Column(modifier = Modifier.padding(20.dp)) {
        Text("1/3", style = KBongTypography.Label2Medium, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        Text("전체적인 경기 직관 소감은 어떤가요?", style = KBongTypography.Heading2)
        Spacer(modifier = Modifier.height(12.dp))
        listOf("다시 보고 싶을 정도로 재미있었어요", "만족스러웠어요", "무난했어요", "별로였어요").forEach {
            Row(modifier = Modifier.padding(vertical = 6.dp)) {
                Icon(painter = painterResource(id = R.drawable.ic_kakao), contentDescription = null, tint = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                Text(it, style = KBongTypography.Body2Reading)
            }
        }
    }
}

@Composable
fun GameLogSubjectiveContent() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text("오늘 경기를 보면서 '야, 야구장에서만 느낄 수 있는 기분이다!' 했던 순간이 있다면?", style = KBongTypography.Heading2)
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.Gray)
        }
        Text("질문에 답하기", style = KBongTypography.Label2Medium, color = Color.Gray)
    }
}

@Preview
@Composable
fun PreviewGameLogWriteScreen() {
    GameLogWriteRoute(
        navController = rememberNavController(),
        gameInfo = DailyGameLog(
            id = 1L,
            startTimeStr = "18:30",
            awayTeamDisplayName = "KT",
            homeTeamDisplayName = "한화",
            stadiumDisplayName = "수원"
        ),
        selectedDate = LocalDate.of(2025, 5, 15)
    )
}

