package com.project.presentation.auth

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.runtime.*
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
import com.project.domain.model.LoginResult
import com.project.presentation.R
import kotlinx.coroutines.delay

@Composable
fun KakaoLoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
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
    val loginResult by authViewModel.loginResult.collectAsState()

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

    LaunchedEffect(loginResult) {
        when (loginResult) {
            is LoginResult.Success -> {
                Log.d("KakaoLogin", "✅ 로그인 성공!")
                navController.navigate("homeScreen")
            }
            is LoginResult.Failure -> {
                val errorMessage = (loginResult as LoginResult.Failure).errorMessage
                if (errorMessage.contains("U002_INVALID_TOKEN")) {
                    Log.d("KakaoLogin", "🚀 회원가입 필요, 회원가입 화면으로 이동")
                    navController.navigate("signUpScreen/${authViewModel.getCurrentIdToken()}")
                } else {
                    Log.e("KakaoLogin", "❌ 로그인 실패: $errorMessage")
                }
            }
            else -> {}
        }
    }
}