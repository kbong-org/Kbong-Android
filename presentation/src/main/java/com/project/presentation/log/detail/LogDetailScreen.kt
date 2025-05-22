package com.project.presentation.log.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.domain.model.log.DailyLogDetail
import com.project.domain.model.log.Detail
import com.project.domain.model.log.Emotion
import com.project.domain.model.log.GameInfo
import com.project.kbong.designsystem.navigationbar.KBongTopBar
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongStatusDestructive
import com.project.kbong.designsystem.theme.KBongTeamTwins
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R
import com.project.presentation.log.Dialog.DeleteLogDialog
import com.project.presentation.log.detail.content.LogDetailFreeContent
import com.project.presentation.log.detail.content.LogDetailObjectiveContent
import com.project.presentation.log.detail.content.LogDetailSubjectiveContent
import kotlinx.coroutines.launch

@Composable
fun LogDetailScreen(
    logId: Long,
    onBack: () -> Unit,
    myTeamColor: Color? = null,
    viewModel: LogDetailViewModel = hiltViewModel()
) {
    val log = viewModel.logDetail
    val isLoading = viewModel.isLoading

    var showMenu by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(logId) {
        viewModel.fetchLogDetail(logId)
        Log.d("LogDetail", "logId: $logId")
    }

    if (isLoading || log == null) {
        CircularProgressIndicator(modifier = Modifier.padding(32.dp))
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // TopBar + 드롭다운 메뉴
        Box {
            KBongTopBar(
                onClickBackButton = onBack,
                rightContent = {
                    Image(
                        painter = painterResource(id = com.project.kbong.designsystem.R.drawable.more),
                        contentDescription = "더보기 아이콘",
                        modifier = Modifier.clickable { showMenu = true }
                    )

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        modifier = Modifier
                            .background(Color.White) // 배경 흰색
                    ) {
                        DropdownMenuItem(
                            text = {
                                Row {
                                    Text("수정하기", color = KBongGrayscaleGray8, style = KBongTypography.Body2Normal)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Image(
                                        painter = painterResource(id = R.drawable.edit),
                                        contentDescription = null,
                                    )
                                }
                            },
                            onClick = {
                                showMenu = false
                                // TODO: 수정 기능 구현
                            }
                        )

                        Divider(color = Color(0xFFEDEDED), thickness = 1.dp)

                        DropdownMenuItem(
                            text = {
                                Row {
                                    Text("삭제하기", color = KBongStatusDestructive,  style = KBongTypography.Body2Normal)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Image(
                                        painter = painterResource(id = R.drawable.delete),
                                        contentDescription = null,
                                    )
                                }
                            },
                            onClick = {
                                showMenu = false
                                showDeleteDialog = true // 다이얼로그 띄우기
                            }
                        )
                    }
                }
            )
        }

        // 스크롤 가능 콘텐츠
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            LogDetailHeader(
                gameInfo = log.gameInfo,
                emotion = Emotion.valueOf(log.emotion)
            )

            when (log.type) {
                "FREE" -> LogDetailFreeContent(log)
                "CHOICE" -> LogDetailObjectiveContent(log, myTeamColor)
                "SHORT" -> LogDetailSubjectiveContent(log, myTeamColor)
            }
        }
    }

    // 삭제 다이얼로그
    if (showDeleteDialog) {
        DeleteLogDialog(
            onConfirm = {
                showDeleteDialog = false
                coroutineScope.launch {
                    val result = viewModel.deleteLog(logId)
                    if (result.isSuccess) {
                        onBack() // 삭제 성공 시 뒤로 가기
                    } else {
                        // 삭제 실패 시 처리 (예: 토스트)
                        Log.e("LogDetail", "삭제 실패: ${result.exceptionOrNull()?.message}")
                    }
                }
            },
            onDismiss = { showDeleteDialog = false }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LogDetailScreenPreview() {
    val dummyLog = DailyLogDetail(
        id = 1,
        type = "SHORT",
        emotion = "NORMAL",
        imagePaths = listOf(
            "https://example.com/image1.jpg",
            "https://example.com/image2.jpg"
        ),
        gameInfo = GameInfo(
            id = 1,
            awayTeamDisplayName = "LG",
            homeTeamDisplayName = "KT",
            stadiumFullName = "잠실",
            date = "2025-05-20"
        ),
        detail = Detail(
            text = "기억에 남는 역전 홈런!",
            questionId = 1,
            questionText = "오늘 경기의 하이라이트는?",
            answer = "기억에 남는 역전 홈런!",
            selectedOption = null,
            answers = null
        )
    )

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        LogDetailHeader(
            gameInfo = dummyLog.gameInfo,
            emotion = Emotion.valueOf(dummyLog.emotion)
        )
        LogDetailSubjectiveContent(dummyLog, myTeamColor = KBongTeamTwins)
    }
}