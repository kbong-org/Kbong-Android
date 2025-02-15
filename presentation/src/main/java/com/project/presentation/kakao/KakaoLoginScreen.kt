package com.project.presentation.kakao

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun KakaoLoginScreen(viewModel: KakaoLoginViewModel = viewModel(), modifier: Modifier = Modifier){
    val context = LocalContext.current
    val userInfo by viewModel.userInfo.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { viewModel.login(context) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFEE500))
        ) {
            Text("카카오 로그인", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        userInfo?.let {
            Spacer(modifier = Modifier.height(20.dp))
            Text(it, fontSize = 16.sp)
        }
    }
}