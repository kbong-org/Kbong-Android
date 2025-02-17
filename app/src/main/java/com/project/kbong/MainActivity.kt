package com.project.kbong

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.kbong.designsystem.theme.KBongTheme
import com.project.presentation.kakao.KakaoLoginScreen
import com.project.presentation.login.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("WrongConstant", "UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 시스템 UI 콘텐츠 위로 확장
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // 하단바 숨기기
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.isAppearanceLightStatusBars = false // 상태바 아이콘 색상 설정
            controller.hide(android.view.WindowInsets.Type.navigationBars()) // 하단 네비게이션바 숨김
        }

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            KBongTheme {
                NavHost(navController, startDestination = "kakao_login") {
                    composable("kakao_login") {
                        KakaoLoginScreen(navController = navController)
                    }
                    composable("login") {
                        LoginScreen(navController)
                    }
                }
            }
        }
    }
}