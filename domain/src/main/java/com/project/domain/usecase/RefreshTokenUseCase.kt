package com.project.domain.usecase

import com.project.domain.model.TokenResult
import com.project.domain.repository.AuthRepository
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(refreshToken: String): TokenResult =
        repository.refreshToken(refreshToken)
}