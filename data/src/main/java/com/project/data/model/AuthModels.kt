package com.project.data.model

data class LoginRequest(
    val idToken: String,
    val provider: String = "KAKAO"
)

data class LoginResponseData(
    val userId: Int,
    val nickname: String,
    val accessToken: String,
    val refreshToken: String
)

data class SignUpRequest(
    val idToken: String,
    val provider: String = "KAKAO",
    val nickname: String,
    val myTeam: String = "NONE"
)

data class ApiResponse<T>(
    val isSuccess: Boolean,
    val data: T?,
    val errorResponse: ErrorResponse?
)

data class ErrorResponse(
    val code: String,
    val message: String
)

data class RefreshTokenRequest(
    val refreshToken: String
)

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)

data class SignUpResponse(
    val isSuccess: Boolean,
    val data: Any? = null,
    val errorResponse: ErrorResponse? = null
)