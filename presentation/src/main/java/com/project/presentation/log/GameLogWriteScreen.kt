package com.project.presentation.log

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
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
import java.util.Locale

enum class LogInputType { IMAGE, TEXT }

@Composable
fun GameLogWriteRoute(
    navController: NavController,
    gameInfo: DailyGameLog,
    selectedDate: LocalDate,
    onSubmit: () -> Unit = {}
) {
    var inputType by remember { mutableStateOf(LogInputType.TEXT) }
    var selectedEmotion by remember { mutableStateOf(com.project.kbong.designsystem.R.drawable.emotion) }

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
                    Text(
                        text = gameInfo.awayTeamDisplayName,
                        style = KBongTypography.Heading2
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "vs",
                        style = KBongTypography.Heading2,
                        color = Color(0xFF3D5AFE)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = gameInfo.homeTeamDisplayName,
                        style = KBongTypography.Heading2
                    )
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
            onEmotionSelected = { selectedEmotion = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (inputType) {
            LogInputType.IMAGE -> GameLogImageContent()
            LogInputType.TEXT -> GameLogTextContent()
        }

        Spacer(modifier = Modifier.weight(1f))

        GameLogToolbar(
            selected = inputType,
            onSelect = { inputType = it }
        )
    }
}

@Composable
fun HeaderGameInfo(
    game: DailyGameLog,
    selectedDate: LocalDate,
    selectedEmotion: Int,
    onEmotionSelected: (Int) -> Unit
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

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_kakao),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(14.dp)
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
                    tint = Color.Gray,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = game.stadiumDisplayName,
                    style = KBongTypography.Label2Medium,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun GameLogTextContent() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "오늘 경기에 대해 자유롭게 작성해보세요!",
            color = Color.Gray,
            style = KBongTypography.Body2Reading
        )
        // TODO: Replace with real text input
    }
}

@Composable
fun GameLogImageContent() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun GameLogToolbar(
    selected: LogInputType,
    onSelect: (LogInputType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        IconToggleButton(
            checked = selected == LogInputType.IMAGE,
            onCheckedChange = { onSelect(LogInputType.IMAGE) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.tip_off),
                contentDescription = null,
                tint = if (selected == LogInputType.IMAGE) Color.Black else Color.Gray
            )
        }

        IconToggleButton(
            checked = selected == LogInputType.TEXT,
            onCheckedChange = { onSelect(LogInputType.TEXT) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.tip_off),
                contentDescription = null,
                tint = if (selected == LogInputType.TEXT) Color.Black else Color.Gray
            )
        }
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
