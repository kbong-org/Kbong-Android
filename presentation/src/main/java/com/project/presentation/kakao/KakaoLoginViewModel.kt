package com.project.presentation.kakao

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class KakaoLoginViewModel : ViewModel() {
    private val _userInfo = MutableStateFlow<String?>(null)
    val userInfo: StateFlow<String?> = _userInfo

    fun login(context: Context, navController: NavController) {
        Log.d("KakaoLogin", "login() 함수 실행됨")

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            Log.d("KakaoLogin", "카카오 로그인 콜백 실행됨")

            if (error != null) {
                Log.e("KakaoLogin", "로그인 실패: ${error.message}")
            } else if (token != null) {
                Log.d("KakaoLogin", "로그인 성공: ${token.accessToken}")
                getUserInfo(navController)
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            Log.d("KakaoLogin", "카카오톡 로그인 가능")
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            Log.d("KakaoLogin", "카카오 계정 로그인 실행")
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

    private fun getUserInfo(navController: NavController) {
        Log.d("KakaoLogin", "getUserInfo() 호출됨")

        UserApiClient.instance.me { user, error ->
            if (user != null) {
                _userInfo.value = "닉네임: ${user.kakaoAccount?.profile?.nickname}\n이메일: ${user.kakaoAccount?.email}"

                Log.d("KakaoLogin", "로그인 성공 후 화면 이동: login")

                navController.navigate("login") {
                    popUpTo("kakao_login") { inclusive = true } // 기존 화면 제거
                }
            } else {
                Log.e("KakaoLogin", "유저 정보 가져오기 실패")
            }
        }
    }
}