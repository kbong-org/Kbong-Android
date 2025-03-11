package com.project.data.repositoryimpl.user

import androidx.datastore.preferences.core.stringPreferencesKey
import com.project.data.local.datastore.KBongDataStore
import com.project.domain.model.user.TokenData
import com.project.domain.repository.user.UserDataStoreRepository
import javax.inject.Inject

class UserDataStoreRepositoryImpl @Inject constructor(
    private val kBongDataStore: KBongDataStore
) : UserDataStoreRepository {

    companion object {
        private const val USER_TOKEN_KEY = "USER_TOKEN"
    }

    private val userTokenKey = stringPreferencesKey(USER_TOKEN_KEY)

    override suspend fun <T> updateUserToken(data: T) {
        kBongDataStore.updateData(data = data, key = userTokenKey)
    }

    override suspend fun getUserToken(): TokenData {
        return kBongDataStore.getData(
            key = userTokenKey,
            type = TokenData::class.java
        ) ?: TokenData()
    }
}