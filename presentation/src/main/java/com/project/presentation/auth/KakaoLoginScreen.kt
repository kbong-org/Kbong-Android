package com.project.presentation.auth

import android.util.Log
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.project.data.LocalNavController
import com.project.domain.model.LoginResult
import com.project.domain.model.SignUpResult
import com.project.presentation.R
import com.project.presentation.home.navigateToHome
import com.project.presentation.navigation.NavigationRoute
import com.project.presentation.signUp.navigateToSignUp
import kotlinx.coroutines.delay

@Composable
fun KakaoLoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val navController: NavController = LocalNavController.current
    var showBottomSheet by remember { mutableStateOf(false) }
    val loginResult by authViewModel.loginResult.collectAsState()

    LaunchedEffect(Unit) {
        delay(2000)
        showBottomSheet = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "이미지",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "디자인 화면 다 나오고 이미지로 넣을 예정!",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
        }

        if (showBottomSheet) {
            AuthLoginBottomSheet(authViewModel, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthLoginBottomSheet(authViewModel: AuthViewModel, navController: NavController) {
    val context = LocalContext.current
    val pagerState = rememberPagerState(pageCount = { 4 })
    //val loginResult by authViewModel.loginResult.collectAsState()

    ModalBottomSheet(
        onDismissRequest = {},
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
            confirmValueChange = { false }
        ),

        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.height(60.dp)
            ) { page ->
                val texts = listOf(
                    "서비스를 통해 다양한 경험을 쌓으세요\n서비스를 통해 다양한 경험을 쌓으세요",
                    "손쉽게 로그인하고 기록을 시작하세요\n손쉽게 로그인하고 기록을 시작하세요",
                    "중요한 순간을 기록하고 저장하세요\n중요한 순간을 기록하고 저장하세요",
                    "간편하게 로그인하고 시작하세요!\n간편하게 로그인하고 시작하세요!"
                )

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = texts[page],
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                repeat(4) { index ->
                    val isSelected = pagerState.currentPage == index

                    Box(
                        modifier = Modifier
                            .size(if (isSelected) 10.dp else 8.dp)
                            .background(if (isSelected) Color.Black else Color.Gray, CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(42.dp))

            Button(
                onClick = { authViewModel.loginWithKakao(context) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFEE500)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
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

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // _signUpResult가 Required 상태면 회원가입 화면으로 네비게이트
    LaunchedEffect(authViewModel.loginResult.collectAsState().value) {
        authViewModel.loginResult.value?.let { loginResult ->
            when (loginResult) {
                is LoginResult.Success -> {
                    Log.d("KakaoLogin", "✅ 로그인 성공 확인 -> 홈으로 이동")
                    navController.navigateToHome(
                        navOptions = navOptions {
                            popUpTo(NavigationRoute.KaKaoLoginScreen.route) { inclusive = true }
                        }
                    )
                }

                is LoginResult.Failure -> {
                    Log.w("KakaoLogin", "⚠️ 로그인 실패 -> 홈으로 이동 실패")
                }
            }
        }
    }

    // 추가: 회원가입 필요 상태(SignUpResult.Required)를 감지하여 회원가입 화면으로 이동
    LaunchedEffect(authViewModel.signUpResult.collectAsState().value) {
        authViewModel.signUpResult.value?.let { signUpResult ->
            when (signUpResult) {
                is SignUpResult.Required -> {
                    Log.d("KakaoLogin", "🚀 회원가입 필요, 회원가입 화면으로 이동")
                    navController.navigateToSignUp(
                        idToken = signUpResult.idToken,
                        navOptions = navOptions {
                            popUpTo(NavigationRoute.KaKaoLoginScreen.route) { inclusive = true }
                        }
                    )
                }

                else -> { /* 다른 상태는 필요시 처리 */
                }
            }
        }
    }
}
