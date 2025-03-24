package com.project.kbong.splash

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.project.domain.model.TokenResult
import com.project.domain.model.user.TokenData
import com.project.domain.usecase.RefreshTokenUseCase
import com.project.domain.usecase.user.GetUserTokenUseCase
import com.project.domain.usecase.user.UpdateUserTokenUseCase
import com.project.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val updateUserTokenUseCase: UpdateUserTokenUseCase
) : BaseViewModel<SplashViewContract.SplashViewState, SplashViewContract.SplashViewEvent, SplashViewContract.SplashViewSideEffect>() {

    init {
        getUserToken()
    }

    override fun createInitialState(): SplashViewContract.SplashViewState {
        return SplashViewContract.SplashViewState()
    }

    override suspend fun handleEvent(event: SplashViewContract.SplashViewEvent) {
        when (event) {
            else -> Unit
        }
    }

    private fun getUserToken() {
        viewModelScope.launch {
            runCatching {
                getUserTokenUseCase()
            }.onSuccess { token ->
                if (token.accessToken.isEmpty()) {
                    Log.d("SplashScreen", "accessToken 없음 → 로그인 필요")
                    mainPostSideEffect(false)
                    return@onSuccess
                }

                Log.d("SplashScreen", "AccessToken 존재 → RefreshToken 시도")
                refreshAccessToken(token)

            }.onFailure {
                Log.d("SplashScree", "getUserToken 실패: ${it.message}")
                mainPostSideEffect(false)
            }
        }
    }

    private suspend fun refreshAccessToken(token: TokenData) {
        runCatching {
            refreshTokenUseCase(token.refreshToken)
        }.onSuccess { result ->
            when (result) {
                is TokenResult.Success -> {
                    Log.d("SplashScreen", "토큰 갱신 성공")
                    val newToken = TokenData(
                        accessToken = result.token.accessToken,
                        refreshToken = result.token.refreshToken
                    )
                    updateUserTokenUseCase(newToken)
                    mainPostSideEffect(true)
                }

                is TokenResult.Failure -> {
                    if (result.errorMessage.contains("JWT expired")) {
                        Log.d("SplashScreen", "토큰 갱신 실패: RefreshToken 만료 → 로그인 필요")
                    } else {
                        Log.d("SplashScreen", "토큰 갱신 실패: ${result.errorMessage}")
                    }
                    updateUserTokenUseCase(TokenData())
                    mainPostSideEffect(false)
                }
            }
        }.onFailure {
            Log.d("SplashScreen", "refreshAccessToken 실패: ${it.message}")
            updateUserTokenUseCase(TokenData())
            mainPostSideEffect(false)
        }
    }

    private fun mainPostSideEffect(isToken: Boolean) {
        postSideEffect(
            SplashViewContract.SplashViewSideEffect.StartMainActivity(
                isToken = isToken
            )
        )
    }
}