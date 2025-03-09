package com.project.kbong.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import com.project.kbong.MainActivity
import com.project.kbong.designsystem.theme.KBongTheme
import com.project.presentation.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

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
            LaunchedEffect(Unit) {
                viewModel.sideEffect.collectLatest { sideEffect ->
                    when (sideEffect) {
                        is SplashViewContract.SplashViewSideEffect.StartMainActivity -> {
                            val intent =
                                Intent(this@SplashActivity, MainActivity::class.java).apply {
                                    putExtra(IS_TOKEN, sideEffect.isToken)
                                }
                            startActivity(intent)
                            finish()
                        }
                    }
                }

            }
            KBongTheme {
                SplashScreen()
            }
        }
    }
}
