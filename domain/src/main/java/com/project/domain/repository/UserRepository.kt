package com.project.domain.repository

import com.project.domain.model.BaseModelContent
import com.project.domain.model.user.UserInfoContent

interface UserRepository {

    suspend fun getUserInfo(): BaseModelContent<UserInfoContent>

    suspend fun updateNickname(nickname: String): BaseModelContent<UserInfoContent>
}