package com.project.presentation.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.domain.model.SignUpResult
import com.project.presentation.R
import com.project.presentation.auth.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
    idToken: String
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })

    // 닉네임과 팀 정보를 상태로 유지
    var nickname by remember { mutableStateOf("") }
    var selectedTeam by remember { mutableStateOf("") }

    // 회원가입 완료 후 결과를 감지하여 홈 화면으로 이동 X (회원가입 요청과 홈 이동을 분리)
    val signUpResult by authViewModel.signUpResult.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // 수직 스크롤 문제 해결
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            when (page) {
                0 -> NicknameInputScreen { nickname = it }
                1 -> TeamSelectionScreen(
                    nickname = nickname,
                    selectedTeam = selectedTeam,
                    onTeamSelect = { selectedTeam = it },
                    onBack = { coroutineScope.launch { pagerState.animateScrollToPage(0) } }
                )
                2 -> SignUpCompleteScreen(nickname = nickname, selectedTeam = selectedTeam)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // "다음" 또는 "시작하기" 버튼
        Button(
            onClick = {
                coroutineScope.launch {
                    when (pagerState.currentPage) {
                        0 -> pagerState.animateScrollToPage(1)
                        1 -> {
                            // 🔹 `selectedTeam`을 서버 ENUM 값으로 변환 후 회원가입 요청
                            val teamEnumMap = mapOf(
                                "KIA 타이거즈" to "KIA",
                                "두산 베어스" to "DOOSAN",
                                "롯데 자이언츠" to "LOTTE",
                                "삼성 라이온즈" to "SAMSUNG",
                                "SSG 랜더스" to "SSG",
                                "NC 다이노스" to "NC",
                                "LG 트윈스" to "LG",
                                "키움 히어로즈" to "KIWOOM",
                                "KT 위즈" to "KT",
                                "한화 이글즈" to "HANHWA",
                                "모두 응원해요" to "NONE"
                            )
                            val myTeamEnum = teamEnumMap[selectedTeam] ?: "NONE"

                            // 회원가입 요청 실행
                            authViewModel.performSignUp(idToken, nickname, myTeamEnum)

                            // 회원가입 요청 후 2번(회원가입 완료 화면)으로 이동
                            pagerState.animateScrollToPage(2)
                        }
                        2 -> {
                            // 2번(회원가입 완료 화면)에서 "시작하기" 버튼 클릭 시 홈으로 이동
                            navController.navigate("homeScreen") {
                                popUpTo("signUpScreen") { inclusive = true }
                            }
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
                    0, 1 -> "다음" // 팀 선택 후 회원가입 요청 + 회원가입 완료 화면으로 이동
                    else -> "시작하기" // 회원가입 완료 후 홈으로 이동
                },
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NicknameInputScreen(onNicknameEntered: (String) -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val maxChar = 10
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 96.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "반가워요!\n닉네임을 설정해주세요.",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { if (it.text.length <= maxChar) text = it },
            placeholder = { Text("사용할 닉네임을 적어주세요", color = Color(0xFFEDEFF2)) },
            textStyle = TextStyle(fontSize = 16.sp),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFEDEFF2),
                focusedBorderColor = Color(0xFF4F61D2)
            ),
            trailingIcon = {
                Text(
                    text = "${text.text.length}/$maxChar",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        )
        Spacer(modifier = Modifier.weight(1f))

        //TODO: 추후 버튼 통일 예정
        Button(
            onClick = {
                keyboardController?.hide()
                onNicknameEntered(text.text) // 닉네임 저장
            },
            enabled = text.text.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F61D2))
        ) {
            Text("다음", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun TeamSelectionScreen(
    nickname: String, // 닉네임 전달됨
    selectedTeam: String,
    onTeamSelect: (String) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(18.dp))
        IconButton(onClick = onBack) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "뒤로가기",
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(55.dp))
        Text(
            text = "$nickname 님이 응원하는 팀을 선택해주세요", // 닉네임 반영됨
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(40.dp))

        val teams = listOf(
            "모두 응원해요",
            "KIA 타이거즈", "두산 베어스",
            "롯데 자이언츠", "삼성 라이온즈",
            "SSG 랜더스", "NC 다이노스",
            "LG 트윈스", "키움 히어로즈",
            "KT 위즈", "한화 이글즈"
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item(span = { GridItemSpan(2) }) {
                TeamButton(team = "모두 응원해요", isSelected = selectedTeam == "모두 응원해요", onTeamSelect = onTeamSelect)
            }
            items(teams.drop(1)) { team ->
                TeamButton(team = team, isSelected = selectedTeam == team, onTeamSelect = onTeamSelect)
            }
        }
    }
}

@Composable
fun TeamButton(
    team: String,
    isSelected: Boolean,
    onTeamSelect: (String) -> Unit,
    modifier: Modifier = Modifier // 기본값 추가
) {
    Box(
        modifier = modifier
            .fillMaxWidth() // 개별 버튼이 자동으로 너비를 맞추도록 설정
            .height(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = if (isSelected) 1.dp else 0.dp,
                color = if (isSelected) Color(0xFF171719) else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .background(if (isSelected) Color.Transparent else Color(0xFFF5F5F5))
            .clickable { onTeamSelect(team) }
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = team,
            fontSize = 16.sp,
            color = if (isSelected) Color(0xFF171719) else Color.Black,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun SignUpCompleteScreen(nickname: String, selectedTeam: String) {
    val teamColors = mapOf(
        "KIA 타이거즈" to Color(0xFFE31937),
        "두산 베어스" to Color(0xFF13274F),
        "롯데 자이언츠" to Color(0xFFC70101),
        "삼성 라이온즈" to Color(0xFF005BAC),
        "SSG 랜더스" to Color(0xFFC60C30),
        "NC 다이노스" to Color(0xFF0D2B68),
        "LG 트윈스" to Color(0xFFED1C24),
        "키움 히어로즈" to Color(0xFF5F0E3B),
        "KT 위즈" to Color(0xFF231F20),
        "한화 이글즈" to Color(0xFFFF8C00),
        "모두 응원해요" to Color(0xFF4F61D2)
    )
    val buttonColor = teamColors[selectedTeam] ?: Color(0xFF4F61D2)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(178.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_sample_mascot),
            contentDescription = "환영 이미지",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "$nickname 님 환영해요!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "직관한 경기를 기록하며\n팀을 응원해 보세요",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            lineHeight = 24.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        // "시작하기" 버튼은 여기선 UI 효과만 처리
        Button(
            onClick = { /* 이 버튼은 실제 네비게이션은 SignUpScreen 상단 버튼에서 처리됨 */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = Color.White
            )
        ) {
            Text("시작하기", fontSize = 16.sp)
        }
    }
}