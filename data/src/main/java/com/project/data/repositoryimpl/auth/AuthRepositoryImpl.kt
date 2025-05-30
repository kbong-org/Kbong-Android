package com.project.data.repositoryimpl.auth

import com.project.data.datasource.AuthRemoteDataSource
import com.project.data.toDomainToken
import com.project.data.toDomainUser
import com.project.domain.model.LoginResult
import com.project.domain.model.SignUpResult
import com.project.domain.model.TokenResult
import com.project.domain.model.user.TokenData
import com.project.domain.repository.AuthRepository
import com.project.domain.repository.user.UserDataStoreRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
    private val userDataStoreRepository: UserDataStoreRepository
) : AuthRepository {
    override suspend fun login(idToken: String): LoginResult {
        val response = remoteDataSource.login(idToken)

        return if (response != null && response.isSuccess && response.data != null) {
            val domainUser = response.data.toDomainUser()
            LoginResult.Success(domainUser)
        } else {
            val errorMsg = response?.errorResponse?.let { "${it.code}: ${it.message}" } ?: "Unknown error"
            LoginResult.Failure(errorMsg)
        }
    }

    override suspend fun refreshToken(refreshToken: String): TokenResult {
        val response = remoteDataSource.refreshToken(refreshToken)

        return if (response != null && response.isSuccess && response.data != null) {
            val domainToken = response.data.toDomainToken()
            TokenResult.Success(domainToken)
        } else {
            TokenResult.Failure(response?.errorResponse?.message ?: "Unknown error")
        }
    }

    override suspend fun signUp(idToken: String, nickname: String, myTeam: String): SignUpResult {
        val response = remoteDataSource.signUp(idToken, nickname, myTeam)

        return if (response != null && response.isSuccess) {
            SignUpResult.Success
        } else {
            SignUpResult.Failure(response?.errorResponse?.message ?: "Unknown error")
        }
    }

    override suspend fun clearToken() {
        userDataStoreRepository.updateUserToken(TokenData())
    }
}