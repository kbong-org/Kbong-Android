package com.project.kbong

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.kbong.designsystem.theme.KBongTheme
import com.project.presentation.HomeScreen
import com.project.presentation.auth.KakaoLoginScreen
import com.project.presentation.signUp.SignUpScreen
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
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            hide(android.view.WindowInsets.Type.navigationBars())
        }
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            KBongTheme {
                // 네비게이션 그래프 정의: startDestination은 "kakaoLoginScreen"으로 설정
                NavHost(navController = navController, startDestination = "kakaoLoginScreen") {
                    composable("kakaoLoginScreen") {
                        KakaoLoginScreen(navController = navController)
                    }
                    // 회원가입 화면: idToken을 인자로 전달 (로그인 API에서 회원가입 필요 판단 시 사용)
                    composable(
                        route = "signUpScreen?{idToken}",
                        arguments = listOf(navArgument("idToken") {
                            type = NavType.StringType
                            defaultValue = ""
                        })
                    ) { backStackEntry ->
                        val idToken = backStackEntry.arguments?.getString("idToken") ?: ""
                        SignUpScreen(navController = navController, idToken = idToken)
                    }
                    composable("homeScreen") {
                        HomeScreen(navController = navController)
                    }
                }
            }
        }
    }
}