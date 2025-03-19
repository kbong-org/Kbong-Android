package com.project.data.datasource.user

import com.project.data.model.BaseModel
import com.project.data.model.user.UserInfoResponse

interface UserDataSource {

    suspend fun getUserInfo(): BaseModel<UserInfoResponse>


}