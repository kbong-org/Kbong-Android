package com.project.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.navOptions
import com.project.data.LocalNavController
import com.project.domain.model.LoginResult
import com.project.domain.model.SignUpResult
import com.project.kbong.designsystem.theme.KBongGrayscaleGray1
import com.project.kbong.designsystem.theme.KBongGrayscaleGray6
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R
import com.project.presentation.home.navigateToHome
import com.project.presentation.navigation.NavigationRoute
import com.project.presentation.signUp.navigateToSignUp

data class LoginPagerItem(
    val imageResId: Int,
    val highlightTitle: String,  // 강조할 텍스트
    val regularTitle: String,    // 일반 텍스트
    val subtitle: String
)

@Composable
fun KakaoLoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val navController = LocalNavController.current
    val pagerState = rememberPagerState(pageCount = { 4 })

    val pagerItems = listOf(
        LoginPagerItem(
            imageResId = R.drawable.login_image_1,
            highlightTitle = "응원하는 팀 선택",
            regularTitle = "",
            subtitle = "선택한 팀의 테마로 이용이 가능해요!"
        ),
        LoginPagerItem(
            imageResId = R.drawable.login_image_2,
            highlightTitle = "한 눈에 확인 가능한",
            regularTitle = "경기정보",
            subtitle = "경기 일정과 결과를 확인해요!"
        ),
        LoginPagerItem(
            imageResId = R.drawable.login_image_3,
            highlightTitle = "1분만에 끝내는",
            regularTitle = " 직관기록",
            subtitle = "다양한 형식으로 재밌게 기록해요!"
        ),
        LoginPagerItem(
            imageResId = R.drawable.login_image_4,
            highlightTitle = "승리요정",
            regularTitle = "을 향해!",
            subtitle = "직관 기록을 쌓아가며 승리요정이 되어보세요!"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            val item = pagerItems[page]

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 회색 배경 박스에 이미지 넣기
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(KBongGrayscaleGray1),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = item.imageResId),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 강조 텍스트
                Row {
                    Text(
                        text = item.highlightTitle,
                        style = KBongTypography.Title,
                        color = KBongPrimary
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = item.regularTitle,
                        style = KBongTypography.Title,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = item.subtitle,
                    style = KBongTypography.Body2Reading,
                    color = KBongGrayscaleGray6
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 인디케이터
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(pagerItems.size) { index ->
                val selected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(if (selected) 10.dp else 8.dp)
                        .background(
                            color = if (selected) Color.Black else Color.LightGray,
                            shape = CircleShape
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        // 카카오 로그인 버튼
        Button(
            onClick = { authViewModel.loginWithKakao(context) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFEE500)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_kakao),
                    contentDescription = "Kakao Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "카카오로 로그인",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }

    LaunchedEffect(authViewModel.loginResult.collectAsState().value) {
        authViewModel.loginResult.value?.let { loginResult ->
            when (loginResult) {
                is LoginResult.Success -> {
                    navController.navigateToHome(
                        navOptions = navOptions {
                            popUpTo(NavigationRoute.KaKaoLoginScreen.route) { inclusive = true }
                        }
                    )
                }

                is LoginResult.Failure -> {
                    // 실패 로그만
                }
                else -> {}
            }
        }
    }

    LaunchedEffect(authViewModel.signUpResult.collectAsState().value) {
        authViewModel.signUpResult.value?.let { signUpResult ->
            if (signUpResult is SignUpResult.Required) {
                navController.navigateToSignUp(
                    idToken = signUpResult.idToken,
                    navOptions = navOptions {
                        popUpTo(NavigationRoute.SignUpScreen.route) { inclusive = true }
                    }
                )
            }
        }
    }
}