package com.project.data.service

import com.project.data.model.BaseModel
import com.project.data.model.user.UserInfoResponse
import retrofit2.http.GET

interface UserService {

    @GET("api/v1/users/me")
    suspend fun getUserInfo(): BaseModel<UserInfoResponse>
}