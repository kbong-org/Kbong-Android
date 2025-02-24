package com.project.presentation.auth

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.project.domain.model.LoginResult
import com.project.domain.model.SignUpResult
import com.project.domain.model.TokenResult
import com.project.domain.usecase.LoginUseCase
import com.project.domain.usecase.RefreshTokenUseCase
import com.project.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _loginResult = MutableStateFlow<LoginResult?>(null)
    val loginResult: StateFlow<LoginResult?> = _loginResult

    private val _signUpResult = MutableStateFlow<SignUpResult?>(null)
    val signUpResult: StateFlow<SignUpResult?> = _signUpResult

    private val _refreshTokenResult = MutableStateFlow<TokenResult?>(null)
    val refreshTokenResult: StateFlow<TokenResult?> = _refreshTokenResult

    private var currentIdToken: String? = null

    fun getCurrentIdToken(): String = currentIdToken ?: ""

    fun loginWithKakao(context: Context) {
        Log.d("KakaoLogin", "✅ loginWithKakao() 실행됨")
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            Log.d("KakaoLogin", "✅ 카카오톡 로그인 시도")
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                handleKakaoLoginResult(token, error)
            }
        } else {
            Log.d("KakaoLogin", "✅ 카카오톡 미설치, 웹 로그인 시도")
            loginWithKakaoAccount(context)
        }
    }

    private fun loginWithKakaoAccount(context: Context) {
        Log.d("KakaoLogin", "✅ loginWithKakaoAccount() 실행됨")
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            handleKakaoLoginResult(token, error)
        }
    }

    private fun handleKakaoLoginResult(token: com.kakao.sdk.auth.model.OAuthToken?, error: Throwable?) {
        if (error != null) {
            Log.e("KakaoLogin", "❌ 카카오 로그인 실패: ${error.message}", error)
            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                Log.w("KakaoLogin", "⚠️ 사용자가 로그인 취소")
                return
            }
        } else if (token != null) {
            Log.d("KakaoLogin", "✅ 로그인 성공, idToken: ${token.idToken}")
            performKakaoLogin(token.idToken ?: "")
        } else {
            Log.e("KakaoLogin", "❌ 로그인 실패: 토큰이 없음")
        }
    }

    fun performKakaoLogin(idToken: String) {
        viewModelScope.launch {
            try {
                Log.d("KakaoLogin", "✅ 서버에 idToken 전송 전 카카오 검증 수행")
                currentIdToken = idToken
                val isValid = validateIdToken(idToken, "c75a1e41de87e3f48c2a41cc0d908ec3")
                if (!isValid) {
                    Log.e("KakaoLogin", "❌ `idToken` 검증 실패, 서버 요청 중단")
                    return@launch
                }
                Log.d("KakaoLogin", "✅ 카카오 토큰 검증 성공, 서버에 idToken 전송")
                val result = loginUseCase(idToken)
                _loginResult.value = result
                when (result) {
                    is LoginResult.Success -> {
                        Log.d("KakaoLogin", "✅ 로그인 성공, AccessToken: ${result.user.accessToken}")
                        refreshAuthToken(result.user.refreshToken)
                    }
                    is LoginResult.Failure -> {
                        if (result.errorMessage.contains("U002_INVALID_TOKEN")) {
                            Log.d("KakaoLogin", "🚀 회원가입 필요, 회원가입 화면으로 이동")
                            _signUpResult.value = SignUpResult.Required(idToken)
                        } else {
                            Log.e("KakaoLogin", "❌ 로그인 실패: ${result.errorMessage}")
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("KakaoLogin", "❌ 서버 요청 중 예외 발생: ${e.localizedMessage}", e)
            }
        }
    }

    fun performSignUp(idToken: String, nickname: String, myTeam: String) {
        viewModelScope.launch {
            try {
                Log.d("SignUp", "✅ 회원가입 요청 진행 중...")
                val result = signUpUseCase(idToken, nickname, myTeam)
                _signUpResult.value = result
                when (result) {
                    is SignUpResult.Success -> {
                        Log.d("SignUp", "✅ 회원가입 성공!")
                    }
                    is SignUpResult.Failure -> {
                        Log.e("SignUp", "❌ 회원가입 실패: ${result.errorMessage}")
                    }
                    else -> {}
                }
            } catch (e: Exception) {
                Log.e("SignUp", "❌ 회원가입 중 예외 발생: ${e.localizedMessage}", e)
            }
        }
    }

    fun refreshAuthToken(refreshToken: String) {
        viewModelScope.launch {
            val result = refreshTokenUseCase(refreshToken)
            _refreshTokenResult.value = result
            when (result) {
                is TokenResult.Success -> {
                    Log.d("KakaoLogin", "✅ 토큰 갱신 성공, 새로운 AccessToken: ${result.token.accessToken}")
                }
                is TokenResult.Failure -> {
                    Log.e("KakaoLogin", "❌ 토큰 갱신 실패: ${result.errorMessage}")
                }
            }
        }
    }

    private fun validateIdToken(idToken: String, expectedAud: String): Boolean {
        return try {
            val decodedJWT = JWT(idToken)
            val issuer = decodedJWT.issuer
            val audience = decodedJWT.audience?.firstOrNull()
            Log.d("KakaoLogin", "✅ `idToken` 검증 결과 - iss: $issuer, aud: $audience")
            if (issuer != "https://kauth.kakao.com") {
                Log.e("KakaoLogin", "❌ `idToken` 발급자가 올바르지 않음")
                return false
            }
            if (audience != expectedAud) {
                Log.e("KakaoLogin", "❌ `idToken`의 aud 값이 서버와 다름 (서버: $expectedAud, 토큰: $audience)")
                return false
            }
            Log.d("KakaoLogin", "✅ `idToken` 검증 성공!")
            true
        } catch (e: Exception) {
            Log.e("KakaoLogin", "❌ `idToken` 검증 실패: ${e.localizedMessage}")
            false
        }
    }
}