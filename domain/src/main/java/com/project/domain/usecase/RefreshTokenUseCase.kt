package com.project.domain.usecase

import com.project.domain.model.TokenResult
import com.project.domain.repository.AuthRepository

class RefreshTokenUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(refreshToken: String): TokenResult {
        return repository.refreshToken(refreshToken)
    }
}