package com.project.presentation.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.domain.model.SignUpResult
import com.project.presentation.auth.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
    idToken: String
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 }) // 3단계로 변경
    var nickname by remember { mutableStateOf("") }
    var selectedTeam by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> NicknameInputScreen(nickname) { nickname = it }
                1 -> TeamSelectionScreen(nickname, selectedTeam) { selectedTeam = it }
                2 -> SignUpCompleteScreen(nickname)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage == 0) {
                        pagerState.animateScrollToPage(1)
                    } else if (pagerState.currentPage == 1) {
                        pagerState.animateScrollToPage(2)
                    } else {
                        authViewModel.performSignUp(
                            idToken = idToken,
                            nickname = nickname,
                            myTeam = selectedTeam
                        )
                        navController.navigate("homeScreen") {
                            popUpTo("signUpScreen") { inclusive = true }
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5865F2))
        ) {
            Text(
                text = when (pagerState.currentPage) {
                    0 -> "다음"
                    1 -> "다음"
                    else -> "시작하기"
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun NicknameInputScreen(nickname: String, onNicknameChange: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("반가워요!\n닉네임을 설정해주세요.", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = nickname,
            onValueChange = { if (it.length <= 10) onNicknameChange(it) },
            placeholder = { Text("사용할 닉네임을 적어주세요") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("${nickname.length}/10", fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun TeamSelectionScreen(nickname: String, selectedTeam: String, onTeamSelect: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("$nickname 님이 응원하는 팀을 선택해주세요", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        val teams = listOf(
            "KIA 타이거즈", "두산 베어스", "롯데 자이언츠", "삼성 라이온즈",
            "SSG 랜더스", "NC 다이노스", "LG 트윈스", "키움 히어로즈",
            "KT 위즈", "한화 이글스"
        )

        Column {
            teams.chunked(2).forEach { rowTeams ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowTeams.forEach { team ->
                        Button(
                            onClick = { onTeamSelect(team) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedTeam == team) Color(0xFF5865F2) else Color.LightGray
                            ),
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(team, color = if (selectedTeam == team) Color.White else Color.Black)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SignUpCompleteScreen(nickname: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text("$nickname 님 환영해요!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("직관한 경기를 기록하며 팀을 응원해 보세요", fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(24.dp))

        // 이미지 추가 가능 (리소스 파일 필요)
        /* Image(
            painter = painterResource(id = R.drawable.signup_complete),
            contentDescription = "가입 완료 이미지",
            modifier = Modifier.size(150.dp)
        ) */
    }
}