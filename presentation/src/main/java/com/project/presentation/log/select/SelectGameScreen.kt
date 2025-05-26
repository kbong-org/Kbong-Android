package com.project.presentation.log.select

import androidx.activity.compose.BackHandler
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
import com.project.kbong.designsystem.theme.KBongAccentButtonBlue
import com.project.kbong.designsystem.theme.KBongAccentButtonBlue10
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongGrayscaleGray5
import com.project.kbong.designsystem.theme.KBongGrayscaleGray7
import com.project.kbong.designsystem.theme.KBongStatusDestructive
import com.project.kbong.designsystem.theme.KBongStatusDestructive10
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.kbong.designsystem.utils.TeamColorMapper
import com.project.presentation.log.navigateToGameLogWrite
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
    myTeamDisplayName: String,
    viewModel: SelectGameViewModel = hiltViewModel(),
    onGameSelected: (DailyGameLog) -> Unit
) {
    val gameList by viewModel.gameList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    var selectedGame by remember { mutableStateOf<DailyGameLog?>(null) }

    val teamColor = remember(myTeamDisplayName) { TeamColorMapper.getTextColor(myTeamDisplayName) }

    // 뒤로가기 한 번으로 나가게 설정
    BackHandler {
        navController.navigate("calendar") {
            popUpTo("selectGame") { inclusive = true }
        }
    }

    LaunchedEffect(selectedDate) {
        viewModel.fetchGames(selectedDate)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp) // 버튼 높이만큼 패딩 확보
                .background(Color.White)
        ) {
            KBongTopBar(
                titleText = "${selectedDate.monthValue}월 ${selectedDate.dayOfMonth}일 " +
                        selectedDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN),
                onClickBackButton = {
                    navController.navigate("calendar") {
                        popUpTo("selectGame") { inclusive = true }
                    }
                }
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
                            GameItem(
                                game = game,
                                isSelected = selectedGame == game,
                                onClick = { selectedGame = game },
                                myTeamDisplayName = myTeamDisplayName
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
            }
        }

        // 하단 고정 버튼
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White) // 버튼 아래 배경 색
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    selectedGame?.let {
                        navController.navigateToGameLogWrite(it.toDto(), selectedDate, myTeamDisplayName)
                    }
                },
                enabled = selectedGame != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = teamColor,
                    contentColor = Color.White
                )
            ) {
                Text("다음")
            }
        }
    }
}

data class GameStatusUi(
    val tagText: String,
    val tagColor: Color,
    val tagBackground: Color,
    val showScore: Boolean
)

@Composable
fun GameItem(
    game: DailyGameLog,
    isSelected: Boolean,
    onClick: () -> Unit,
    myTeamDisplayName: String
) {
    val teamColor = remember(myTeamDisplayName) {
        TeamColorMapper.getTextColor(myTeamDisplayName)
    }

    val borderColor = if (isSelected) teamColor else Color.Transparent
    val shape: Shape = MaterialTheme.shapes.medium

    // 상태 분기
    val statusUi = when (game.status) {
        "FINISHED" -> GameStatusUi("종료", KBongGrayscaleGray7, KBongGrayscaleGray2, true)
        "CANCELLED" -> GameStatusUi("취소", KBongStatusDestructive, KBongStatusDestructive10, false)
        "SUSPENDED" -> GameStatusUi("연기", KBongAccentButtonBlue, KBongAccentButtonBlue10, false)
        else -> null
    }

    val tagText = statusUi?.tagText
    val tagColor = statusUi?.tagColor
    val tabBgColor = statusUi?.tagBackground
    val showScore = statusUi?.showScore ?: false

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
                // Away
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(game.awayTeamDisplayName, style = KBongTypography.Body1NormalSemiBold)
                    if (showScore) {
                        Text(
                            text = game.awayTeamScore?.toString() ?: "-",
                            color = if ((game.awayTeamScore ?: 0) > (game.homeTeamScore ?: 0)) KBongAccentButtonBlue else KBongGrayscaleGray5,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    } else {
                        Spacer(modifier = Modifier.height(spToDp(16f)))
                    }
                }

                // Center
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    tagText?.let {
                        Box(
                            modifier = Modifier
                                .background(tabBgColor!!, shape = RoundedCornerShape(6.dp))
                                .height(20.dp)
                                .defaultMinSize(minWidth = 38.dp)
                                .padding(horizontal = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = it,
                                fontSize = 10.sp,
                                lineHeight = 12.sp,
                                color = tagColor!!,
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

                // Home
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(game.homeTeamDisplayName, style = KBongTypography.Body1NormalSemiBold)
                    if (showScore) {
                        Text(
                            text = game.homeTeamScore?.toString() ?: "-",
                            color = if ((game.homeTeamScore ?: 0) > (game.awayTeamScore ?: 0)) KBongAccentButtonBlue else KBongGrayscaleGray5,
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
            stadiumDisplayName = "수원",
            status = "FINISHED"
        ),
        isSelected = true,
        onClick = {},
        myTeamDisplayName = "LG"
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
            stadiumDisplayName = "문학",
            status = "SCHEDULED"
        ),
        isSelected = false,
        onClick = {},
        myTeamDisplayName = "LG"
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewGameItemCancelled() {
    GameItem(
        game = DailyGameLog(
            id = 3L,
            startTimeStr = "18:30",
            awayTeamDisplayName = "LG",
            homeTeamDisplayName = "NC",
            stadiumDisplayName = "잠실",
            status = "CANCELLED"
        ),
        isSelected = false,
        onClick = {},
        myTeamDisplayName = "LG"
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewGameItemSuspended() {
    GameItem(
        game = DailyGameLog(
            id = 3L,
            startTimeStr = "18:30",
            awayTeamDisplayName = "LG",
            homeTeamDisplayName = "NC",
            stadiumDisplayName = "잠실",
            status = "SUSPENDED"
        ),
        isSelected = false,
        onClick = {},
        myTeamDisplayName = "LG"
    )
}