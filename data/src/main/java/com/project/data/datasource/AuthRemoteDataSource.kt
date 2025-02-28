package com.project.data.datasource

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.data.model.*
import com.project.data.remote.AuthService

class AuthRemoteDataSource(private val authService: AuthService) {

    suspend fun login(idToken: String): ApiResponse<LoginResponseData>? {
        val request = LoginRequest(idToken = idToken)
        val gson = Gson()

        Log.d("KakaoLogin", "📤 서버로 보낼 데이터: ${gson.toJson(request)}")

        val response = authService.login(request)
        Log.d("KakaoLogin", "✅ 서버 응답 코드: ${response.code()}")

        val errorBody = response.errorBody()?.string()
        Log.d("KakaoLogin", "✅ 서버 응답 본문: $errorBody")

        return if (response.isSuccessful) {
            response.body()
        } else {
            try {
                val type = object : TypeToken<ApiResponse<LoginResponseData>>() {}.type
                gson.fromJson(errorBody, type)
            } catch (e: Exception) {
                ApiResponse(false, null, ErrorResponse("Unknown", "Unknown error"))
            }
        }
    }

    suspend fun refreshToken(refreshToken: String): ApiResponse<TokenResponse>? {
        val request = RefreshTokenRequest(refreshToken = refreshToken)
        val response = authService.refreshToken(request)

        return if (response.isSuccessful) response.body() else null
    }

    suspend fun signUp(idToken: String, nickname: String, myTeam: String): SignUpResponse? {
        val request = SignUpRequest(idToken = idToken, nickname = nickname, myTeam = myTeam)
        val gson = Gson()

        Log.d("KakaoLogin", "📤 서버로 보낼 데이터 (회원가입): ${gson.toJson(request)}")

        val response = authService.signUp(request)
        Log.d("KakaoLogin", "✅ 서버 응답 코드 (회원가입): ${response.code()}")
        Log.d("KakaoLogin", "✅ 서버 응답 본문 (회원가입): ${response.errorBody()?.string()}")

        return if (response.isSuccessful) response.body() else {
            try {
                val type = object : TypeToken<SignUpResponse>() {}.type
                gson.fromJson(response.errorBody()?.string(), type)
            } catch (e: Exception) {
                SignUpResponse(false, null, ErrorResponse("Unknown", "Unknown error"))
            }
        }
    }
}