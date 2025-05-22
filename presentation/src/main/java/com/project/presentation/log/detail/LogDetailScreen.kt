package com.project.presentation.log.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.project.kbong.designsystem.theme.KBongTeamTwins
import com.project.presentation.log.detail.content.LogDetailFreeContent
import com.project.presentation.log.detail.content.LogDetailObjectiveContent
import com.project.presentation.log.detail.content.LogDetailSubjectiveContent

@Composable
fun LogDetailScreen(
    logId: Long,
    onBack: () -> Unit,
    myTeamColor: Color? = null,
    viewModel: LogDetailViewModel = hiltViewModel()
) {
    val log = viewModel.logDetail
    val isLoading = viewModel.isLoading

    LaunchedEffect(logId) {
        viewModel.fetchLogDetail(logId)
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
        KBongTopBar(
            onClickBackButton = onBack,
            rightContent = {
                Image(
                    painter = painterResource(id = com.project.kbong.designsystem.R.drawable.more),
                    contentDescription = "더보기 아이콘"
                )
            }
        )

        // 콘텐츠 부분만 스크롤
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