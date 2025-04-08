package com.project.data.datasource.user

import com.project.data.model.BaseModel
import com.project.data.model.user.UserInfoResponse
import com.project.data.request.user.UpdateNicknameRequest
import com.project.data.service.UserService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserDataSource {

    override suspend fun getUserInfo(): BaseModel<UserInfoResponse> {
        return userService.getUserInfo()
    }

    override suspend fun updateNickname(nickname: String): BaseModel<UserInfoResponse> {
        return userService.updateNickname(UpdateNicknameRequest(nickname))
    }
}