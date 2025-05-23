package com.project.kbong

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.project.kbong.designsystem.theme.KBongTheme
import com.project.kbong.splash.SplashActivity.Companion.IS_TOKEN
import com.project.presentation.navigation.NavigationRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val isToken by lazy { intent.getBooleanExtra(IS_TOKEN, false) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val startDestination = if (isToken) {
                NavigationRoute.CalendarScreen.route
            } else {
                NavigationRoute.KaKaoLoginScreen.route
            }

            KBongTheme {
                KBongApp(
                    startDestination = startDestination
                )
            }
        }
    }
}
