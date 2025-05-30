package com.project.data.service

import com.project.data.model.ApiResponse
import com.project.data.model.LoginRequest
import com.project.data.model.LoginResponseData
import com.project.data.model.RefreshTokenRequest
import com.project.data.model.SignUpRequest
import com.project.data.model.SignUpResponse
import com.project.data.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<LoginResponseData>>

    @POST("/api/v1/auth/token")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): Response<ApiResponse<TokenResponse>>

    @POST("/api/v1/users/sign-up")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @DELETE("/api/v1/users")
    suspend fun withdraw(): Response<ApiResponse<Any>>
}