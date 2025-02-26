package com.project.domain.repository

import com.project.domain.model.LoginResult
import com.project.domain.model.SignUpResult
import com.project.domain.model.TokenResult

interface AuthRepository {
    suspend fun login(idToken: String): LoginResult
    suspend fun refreshToken(refreshToken: String): TokenResult
    suspend fun signUp(idToken: String, nickname: String, myTeam: String): SignUpResult
}