package com.project.presentation.kakao

import com.project.presentation.R
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun KakaoLoginScreen(
    navController: NavController,
    viewModel: KakaoLoginViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    // 2초 후 바텀시트 표시
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
            LoginBottomSheet(viewModel, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginBottomSheet(viewModel: KakaoLoginViewModel, navController: NavController) {
    val context = LocalContext.current
    val pagerState = rememberPagerState(pageCount = { 4 })

    ModalBottomSheet(
        onDismissRequest = {}, // 닫기 버튼 제거
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
            confirmValueChange = { false } // 바텀시트를 내릴 수 없도록 설정
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 스와이프 가능한 텍스트
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.height(60.dp)
            ) { page ->
                val texts = listOf(
                    "직관한 경기를 기록해요\n직관한 경기를 기록해요",
                    "새로운 스포츠 경험을 기록하세요\n새로운 스포츠 경험을 기록하세요",
                    "잊지 않고 소중한 순간을 남기세요\n잊지 않고 소중한 순간을 남기세요",
                    "카카오로 로그인하고 시작해요!\n카카오로 로그인하고 시작해요!"
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

            // 페이지 인디케이터 (도트 간격 10dp 적용)
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

            // 카카오 로그인 버튼
            Button(
                onClick = {
                    viewModel.login(context, navController)
                },
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
                        painter = painterResource(id = R.drawable.ic_kakao), // 카카오 로고 리소스 추가
                        contentDescription = "Kakao Icon",
                        tint = Color.Unspecified, // 원래 색상을 유지하도록 설정
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // 아이콘과 텍스트 간격 조정
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
}