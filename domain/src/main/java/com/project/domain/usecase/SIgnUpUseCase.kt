package com.project.domain.usecase

import com.project.domain.model.SignUpResult
import com.project.domain.repository.AuthRepository

class SignUpUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(idToken: String, nickname: String, myTeam: String): SignUpResult =
        repository.signUp(idToken, nickname, myTeam)
}