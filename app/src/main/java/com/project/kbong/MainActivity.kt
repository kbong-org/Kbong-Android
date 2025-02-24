package com.project.kbong

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
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
                NavHost(navController = navController, startDestination = "kakaoLoginScreen") {
                    composable("kakaoLoginScreen") {
                        KakaoLoginScreen(navController = navController)
                    }
                    // 회원가입 화면: idToken을 인자로 전달 (실제 앱에서는 viewModel이나 safeArgs를 이용해 전달)
                    composable(
                        route = "signUpScreen?{idToken}",
                        arguments = listOf(navArgument("idToken") { type = NavType.StringType; defaultValue = "" })
                    ) { backStackEntry ->
                        val idToken = backStackEntry.arguments?.getString("idToken") ?: ""
                        SignUpScreen(navController = navController, idToken = idToken)
                    }
                    composable("homeScreen") {
                        // 홈 화면 예시: 간단한 메시지 출력
                        HomeScreen(navController = navController)
                    }
                }
            }
        }
    }
}