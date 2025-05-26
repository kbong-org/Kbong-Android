package com.project.data.repositoryimpl.user

import com.project.data.datasource.user.UserDataSource
import com.project.data.mapper.toBaseDomain
import com.project.data.mapper.user.toDomain
import com.project.domain.model.BaseModelContent
import com.project.domain.model.user.UserInfoContent
import com.project.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun getUserInfo(): BaseModelContent<UserInfoContent> {
        return userDataSource.getUserInfo().toBaseDomain {
            it.toDomain()
        }
    }

    override suspend fun updateNickname(nickname: String): BaseModelContent<UserInfoContent> {
        return userDataSource.updateNickname(nickname).toBaseDomain {
            it.toDomain()  // Assuming the API returns the updated user info after the nickname update
        }
    }
}