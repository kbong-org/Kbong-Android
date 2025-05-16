package com.project.presentation.log

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.data.model.log.toDto
import com.project.domain.model.day.DailyGameLog
import com.project.kbong.designsystem.navigationbar.KBongTopBar
import com.project.kbong.designsystem.theme.KBongTypography
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Composable
fun spToDp(sp: Float): Dp {
    val density = LocalDensity.current
    return with(density) { sp.sp.toDp() }
}

@Composable
fun SelectGameScreen(
    navController: NavController,
    selectedDate: LocalDate,
    viewModel: SelectGameViewModel = hiltViewModel(),
    onGameSelected: (DailyGameLog) -> Unit
) {
    val gameList by viewModel.gameList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    var selectedGame by remember { mutableStateOf<DailyGameLog?>(null) }

    LaunchedEffect(selectedDate) {
        viewModel.fetchGames(selectedDate)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        KBongTopBar(
            titleText = "${selectedDate.monthValue}월 ${selectedDate.dayOfMonth}일 " +
                    selectedDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN),
            onClickBackButton = { navController.popBackStack() }
        )

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "기록할 경기를 선택해주세요",
                style = KBongTypography.Heading2,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(20.dp))

            when {
                isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                errorMessage != null -> Text(
                    text = errorMessage ?: "오류 발생",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                else -> LazyColumn {
                    items(gameList) { game ->
                        val finished = game.homeTeamScore != null && game.awayTeamScore != null

                        GameItem(
                            game = game,
                            isSelected = selectedGame == game,
                            isFinished = finished,
                            onClick = {
                                selectedGame = game
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                onClick = {
                    selectedGame?.let {
                        navController.navigateToGameLogWrite(it.toDto(), selectedDate)
                    }
                },
                enabled = selectedGame != null
            ) {
                Text("다음")
            }
        }
    }
}

@Composable
fun GameItem(
    game: DailyGameLog,
    isSelected: Boolean,
    isFinished: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) Color(0xFF3D5AFE) else Color.Transparent
    val shape: Shape = MaterialTheme.shapes.medium

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .clickable { onClick() },
        shadowElevation = 0.dp,
        shape = shape,
        border = if (isSelected) BorderStroke(0.5.dp, borderColor) else null,
        color = Color(0xFFF9F9FA)
    ) {
        Column(modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Away Team
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = game.awayTeamDisplayName,
                        style = KBongTypography.Body1NormalSemiBold
                    )
                    if (isFinished) {
                        Text(
                            text = game.awayTeamScore?.toString() ?: "-",
                            color = when {
                                (game.awayTeamScore ?: 0) > (game.homeTeamScore ?: 0) -> Color(
                                    0xFF3D5AFE
                                )

                                else -> Color(0xFFB0B0B0)
                            },
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    } else {
                        Spacer(modifier = Modifier.height(spToDp(16f)))
                    }
                }

                // Center (종료 태그 + 시간 + 경기장)
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (isFinished) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFEDEDED), shape = RoundedCornerShape(6.dp))
                                .height(20.dp)
                                .defaultMinSize(minWidth = 38.dp)
                                .padding(horizontal = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "종료",
                                fontSize = 10.sp,
                                lineHeight = 12.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                    }

                    Text(
                        text = game.startTimeStr,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = game.stadiumDisplayName,
                        style = KBongTypography.Label2Medium,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }

                // Home Team
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = game.homeTeamDisplayName,
                        style = KBongTypography.Body1NormalSemiBold
                    )
                    if (isFinished) {
                        Text(
                            text = game.homeTeamScore?.toString() ?: "-",
                            color = when {
                                (game.homeTeamScore ?: 0) > (game.awayTeamScore ?: 0) -> Color(
                                    0xFF3D5AFE
                                )

                                else -> Color(0xFFB0B0B0)
                            },
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    } else {
                        Spacer(modifier = Modifier.height(spToDp(16f)))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameItemFinished() {
    GameItem(
        game = DailyGameLog(
            id = 1L,
            startTimeStr = "18:30",
            awayTeamDisplayName = "KT",
            awayTeamScore = 5,
            homeTeamScore = 2,
            homeTeamDisplayName = "한화",
            stadiumDisplayName = "수원"
        ),
        isSelected = true,
        onClick = {},
        isFinished = true
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewGameItemScheduled() {
    GameItem(
        game = DailyGameLog(
            id = 2L,
            startTimeStr = "18:30",
            awayTeamDisplayName = "두산",
            homeTeamDisplayName = "SSG",
            stadiumDisplayName = "문학"
        ),
        isSelected = false,
        onClick = {},
        isFinished = false
    )
}