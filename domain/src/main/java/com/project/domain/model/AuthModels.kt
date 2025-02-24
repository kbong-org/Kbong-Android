package com.project.domain.model

// 사용자 정보 모델
data class User(
    val id: Int,
    val nickname: String,
    val accessToken: String,
    val refreshToken: String
)

// 로그인 결과 모델
sealed class LoginResult {
    data class Success(val user: User) : LoginResult()
    data class Failure(val errorMessage: String) : LoginResult()
}

// 토큰 모델
data class Token(
    val accessToken: String,
    val refreshToken: String
)

// 토큰 결과 모델
sealed class TokenResult {
    data class Success(val token: Token) : TokenResult()
    data class Failure(val errorMessage: String) : TokenResult()
}

// 회원가입 결과 모델
sealed class SignUpResult {
    object Success : SignUpResult()
    data class Failure(val errorMessage: String) : SignUpResult()
    data class Required(val idToken: String) : SignUpResult() // 회원가입 필요 시 idToken 보존
}