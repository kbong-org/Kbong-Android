package com.project.domain.repository.user

import com.project.domain.model.user.TokenData

interface UserDataStoreRepository {
    suspend fun <T> updateUserToken(data: T)
    suspend fun getUserToken(): TokenData

}