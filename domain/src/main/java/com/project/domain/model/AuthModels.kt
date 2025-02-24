package com.project.domain.model

data class User(
    val id: Int,
    val nickname: String,
    val accessToken: String,
    val refreshToken: String
)

sealed class LoginResult {
    data class Success(val user: User) : LoginResult()
    data class Failure(val errorMessage: String) : LoginResult()
}

data class Token(
    val accessToken: String,
    val refreshToken: String
)

sealed class TokenResult {
    data class Success(val token: Token) : TokenResult()
    data class Failure(val errorMessage: String) : TokenResult()
}

sealed class SignUpResult {
    object Success : SignUpResult()
    data class Failure(val errorMessage: String) : SignUpResult()
    data class Required(val idToken: String) : SignUpResult() // 신규회원인 경우
}