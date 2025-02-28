package com.project.domain.usecase

import com.project.domain.model.LoginResult
import com.project.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(idToken: String): LoginResult =
        repository.login(idToken)
}