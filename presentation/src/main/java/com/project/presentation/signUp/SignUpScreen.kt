package com.project.presentation.signUp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.project.data.LocalNavController
import com.project.domain.model.SignUpResult
import com.project.kbong.designsystem.theme.KBongGrayscaleGray1
import com.project.kbong.designsystem.theme.KBongGrayscaleGray10
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongGrayscaleGray5
import com.project.kbong.designsystem.theme.KBongGrayscaleGray7
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongTeamBears
import com.project.kbong.designsystem.theme.KBongTeamEagles
import com.project.kbong.designsystem.theme.KBongTeamGiants
import com.project.kbong.designsystem.theme.KBongTeamHeroes
import com.project.kbong.designsystem.theme.KBongTeamLions
import com.project.kbong.designsystem.theme.KBongTeamNc
import com.project.kbong.designsystem.theme.KBongTeamSsg
import com.project.kbong.designsystem.theme.KBongTeamTigers
import com.project.kbong.designsystem.theme.KBongTeamTwins
import com.project.kbong.designsystem.theme.KBongTheme
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R
import com.project.presentation.auth.AuthViewModel
import com.project.presentation.home.navigateToHome
import com.project.presentation.navigation.NavigationRoute
import kotlinx.coroutines.launch

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel,
    idToken: String
) {
    val navController: NavController = LocalNavController.current
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })

    var nickname by rememberSaveable { mutableStateOf("") }
    var selectedTeam by remember { mutableStateOf("") }

    val signUpResult by authViewModel.signUpResult.collectAsState()

    val teamColors = mapOf(
        "KIA 타이거즈" to KBongTeamTigers,
        "두산 베어스" to KBongTeamBears,
        "롯데 자이언츠" to KBongTeamGiants,
        "삼성 라이온즈" to KBongTeamLions,
        "SSG 랜더스" to KBongTeamSsg,
        "NC 다이노스" to KBongTeamNc,
        "LG 트윈스" to KBongTeamTwins,
        "키움 히어로즈" to KBongTeamHeroes,
        "KT 위즈" to Color(0xFF000000),
        "한화 이글즈" to KBongTeamEagles,
        "모두 응원해요" to KBongPrimary
    )

    LaunchedEffect(signUpResult) {
        when (signUpResult) {
            is SignUpResult.Failure -> {
                val error = signUpResult as SignUpResult.Failure
                Log.e("SignUp", "회원가입 실패: ${error.errorMessage}")
            }
            is SignUpResult.Success -> {
                Log.d("SignUp", "회원가입 성공")
            }
            else -> {}
        }
    }

    val imeBottom = WindowInsets.ime.getBottom(LocalDensity.current)
    val keyboardHeight = with(LocalDensity.current) { imeBottom.toDp() }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->
                when (page) {
                    0 -> NicknameInputScreen(nickname) { nickname = it }
                    1 -> TeamSelectionScreen(nickname, selectedTeam, { selectedTeam = it }) {
                        coroutineScope.launch { pagerState.animateScrollToPage(0) }
                    }
                    2 -> SignUpCompleteScreen(nickname, selectedTeam)
                }
            }
        }

        // 버튼 텍스트
        val buttonText = when (pagerState.currentPage) {
            0, 1 -> "다음"
            else -> "시작하기"
        }

        // 버튼 활성화 여부
        val isButtonEnabled = when (pagerState.currentPage) {
            0 -> nickname.isNotEmpty()
            1 -> selectedTeam.isNotEmpty()
            else -> true
        }

        // 버튼 컬러 설정
        val buttonColor = when (pagerState.currentPage) {
            2 -> teamColors[selectedTeam] ?: KBongPrimary
            else -> if (isButtonEnabled) KBongPrimary else KBongGrayscaleGray2
        }

        val keyboardController = LocalSoftwareKeyboardController.current

        // 키보드 위로 올라가는 버튼
        Button(
            onClick = {
                coroutineScope.launch {
                    when (pagerState.currentPage) {
                        0 -> {
                            keyboardController?.hide()
                            pagerState.animateScrollToPage(1)
                        }
                        1 -> {
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
                            authViewModel.performSignUp(idToken, nickname, myTeamEnum)
                            pagerState.animateScrollToPage(2)
                        }
                        2 -> navController.navigateToHome(
                            navOptions = navOptions {
                                popUpTo(NavigationRoute.SignUpScreen.route) { inclusive = true }
                            }
                        )
                    }
                }
            },
            enabled = isButtonEnabled,
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    bottom = if (keyboardHeight > 0.dp) (keyboardHeight - 32.dp).coerceAtLeast(0.dp) else 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = buttonText,
                style = KBongTypography.Body1Normal,
                color = Color.White
            )
        }
    }
}

@Composable
fun NicknameInputScreen(nickname: String, onNicknameEntered: (String) -> Unit) {

    val maxChar = 10

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 96.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Spacer(modifier = Modifier.height(96.dp))

        Text(
            text = "반가워요!\n닉네임을 설정해주세요.",
            style = KBongTypography.Title,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = nickname,
            onValueChange = { if (it.length <= maxChar) onNicknameEntered(it) },
            placeholder = { Text("사용할 닉네임을 적어주세요", color = KBongGrayscaleGray5) },
            textStyle = TextStyle(fontSize = 16.sp),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = KBongGrayscaleGray2,
                focusedBorderColor = KBongPrimary,
            ),
            trailingIcon = {
                Text(
                    text = "${nickname.length}/$maxChar",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        )
        Spacer(modifier = Modifier.weight(1f))
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
            text = "$nickname 님이",
            style = KBongTypography.Title,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "응원하는 팀을 선택해주세요",
            style = KBongTypography.Title,
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
                color = if (isSelected) KBongGrayscaleGray10 else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .background(KBongGrayscaleGray1)
            .clickable { onTeamSelect(team) }
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = team,
            style = KBongTypography.Body1Normal,
            color = if (isSelected) Color.Black else KBongGrayscaleGray7,
        )
    }
}

@Composable
fun SignUpCompleteScreen(nickname: String, selectedTeam: String) {

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
            style = KBongTypography.Title,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "직관한 경기를 기록하며\n팀을 응원해 보세요",
            style = KBongTypography.Body1Normal,
            textAlign = TextAlign.Center,
            color = Color.Gray,
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    KBongTheme {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val screenHeight = maxHeight
            val imeBottom = WindowInsets.ime.getBottom(LocalDensity.current)
            val isKeyboardVisible = imeBottom > 0

            var nickname by rememberSaveable { mutableStateOf("") }
            var selectedTeam by remember { mutableStateOf("") }
            val pagerState = rememberPagerState(initialPage = 1, pageCount = { 3 })
            val coroutineScope = rememberCoroutineScope()

            val teamColors = mapOf(
                "KIA 타이거즈" to KBongTeamTigers,
                "두산 베어스" to KBongTeamBears,
                "롯데 자이언츠" to KBongTeamGiants,
                "삼성 라이온즈" to KBongTeamLions,
                "SSG 랜더스" to KBongTeamSsg,
                "NC 다이노스" to KBongTeamNc,
                "LG 트윈스" to KBongTeamTwins,
                "키움 히어로즈" to KBongTeamHeroes,
                "KT 위즈" to Color(0xFF000000),
                "한화 이글즈" to KBongTeamEagles,
                "모두 응원해요" to KBongPrimary
            )

            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) { page ->
                        when (page) {
                            0 -> NicknameInputScreen(nickname) { nickname = it }
                            1 -> TeamSelectionScreen(nickname, selectedTeam, { selectedTeam = it }) {
                                coroutineScope.launch { pagerState.animateScrollToPage(0) }
                            }
                            2 -> SignUpCompleteScreen(nickname, selectedTeam)
                        }
                    }
                }

                val buttonText = when (pagerState.currentPage) {
                    0, 1 -> "다음"
                    else -> "시작하기"
                }

                val isButtonEnabled = when (pagerState.currentPage) {
                    0 -> nickname.isNotEmpty()
                    1 -> selectedTeam.isNotEmpty()
                    else -> true
                }

                val buttonColor = when (pagerState.currentPage) {
                    2 -> teamColors[selectedTeam] ?: KBongPrimary
                    else -> if (isButtonEnabled) KBongPrimary else KBongGrayscaleGray2
                }

                // 버튼
                Button(
                    onClick = {
                        coroutineScope.launch {
                            when (pagerState.currentPage) {
                                0 -> pagerState.animateScrollToPage(1)
                                1 -> pagerState.animateScrollToPage(2)
                                2 -> {} // 홈 이동 생략
                            }
                        }
                    },
                    enabled = isButtonEnabled,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(
                            bottom = if (isKeyboardVisible) imeBottom.dp else 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                ) {
                    Text(
                        text = buttonText,
                        style = KBongTypography.Body1Normal,
                        color = Color.White
                    )
                }
            }
        }
    }
}