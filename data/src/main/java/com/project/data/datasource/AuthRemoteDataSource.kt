package com.project.data.datasource

import android.util.Log
import com.google.gson.Gson
import com.project.data.model.*
import com.project.data.remote.AuthService

class AuthRemoteDataSource(private val authService: AuthService) {

    suspend fun login(idToken: String): ApiResponse<LoginResponseData>? {
        val request = LoginRequest(idToken = idToken)
        val requestJson = Gson().toJson(request)

        Log.d("KakaoLogin", "📤 [Login] 서버로 보낼 데이터: $requestJson")

        val response = authService.login(request)

        Log.d("KakaoLogin", "✅ [Login] 서버 응답 코드: ${response.code()}")
        Log.d("KakaoLogin", "✅ [Login] 서버 응답 본문: ${response.errorBody()?.string()}")

        return if (response.isSuccessful) response.body() else null
    }

    suspend fun refreshToken(refreshToken: String): ApiResponse<TokenResponse>? {
        val request = RefreshTokenRequest(refreshToken = refreshToken)
        val response = authService.refreshToken(request)

        return if (response.isSuccessful) response.body() else null
    }

    suspend fun signUp(idToken: String, nickname: String, myTeam: String): SignUpResponse? {
        val request = SignUpRequest(idToken = idToken, nickname = nickname, myTeam = myTeam)
        val requestJson = Gson().toJson(request)

        Log.d("SignUp", "📤 [SignUp] 서버로 보낼 데이터: $requestJson")

        val response = authService.signUp(request)

        Log.d("SignUp", "✅ [SignUp] 서버 응답 코드: ${response.code()}")
        Log.d("SignUp", "✅ [SignUp] 서버 응답 본문: ${response.errorBody()?.string()}")

        return if (response.isSuccessful) response.body() else null
    }
}