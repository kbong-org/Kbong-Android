package com.project.data.datasource

import com.project.data.model.ApiResponse
import com.project.data.model.LoginResponseData
import com.project.data.model.SignUpResponse
import com.project.data.model.TokenResponse

interface AuthRemoteDataSource {
    suspend fun login(idToken: String): ApiResponse<LoginResponseData>?
    suspend fun refreshToken(refreshToken: String): ApiResponse<TokenResponse>?
    suspend fun signUp(idToken: String, nickname: String, myTeam: String): SignUpResponse?
    suspend fun withdraw(): ApiResponse<Any>?
}