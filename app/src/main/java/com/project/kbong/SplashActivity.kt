package com.project.kbong

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.project.kbong.designsystem.theme.KBongTheme
import com.project.presentation.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    companion object {
        const val IS_TOKEN = "IS_TOKEN"
    }

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KBongTheme {
                SplashScreen(
                    onTimeout = {
                        val intent = Intent(this@SplashActivity, MainActivity::class.java).apply {
                            putExtra(IS_TOKEN, viewModel.state.value.isToken)
                        }
                        startActivity(intent)
                        finish()
                    }
                )
            }
        }
    }
}
