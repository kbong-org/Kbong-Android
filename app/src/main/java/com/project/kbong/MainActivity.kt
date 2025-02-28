package com.project.kbong

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.project.kbong.designsystem.theme.KBongTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KBongTheme {
                KBongApp()
            }
        }
    }
}

// todo 합치기
//WindowCompat.setDecorFitsSystemWindows(window, false)
//WindowInsetsControllerCompat(window, window.decorView).apply {
//    isAppearanceLightStatusBars = false
//    hide(android.view.WindowInsets.Type.navigationBars())
//}
//enableEdgeToEdge()
//setContent {
//    val navController = rememberNavController()
//    KBongTheme {
//        NavHost(navController = navController, startDestination = "kakaoLoginScreen") {
//            composable("kakaoLoginScreen") {
//                KakaoLoginScreen(navController = navController)
//            }
//            composable(
//                route = "signUpScreen?idToken={idToken}",
//                arguments = listOf(navArgument("idToken") { type = NavType.StringType; defaultValue = "" })
//            ) { backStackEntry ->
//                val idToken = backStackEntry.arguments?.getString("idToken") ?: ""
//                SignUpScreen(navController = navController, idToken = idToken)
//            }
//            composable("homeScreen") {
//                HomeScreen(navController = navController)
//            }
//        }
//    }
//}
