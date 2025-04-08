package com.project.data.service

import com.project.data.model.BaseModel
import com.project.data.model.user.UserInfoResponse
import com.project.data.request.user.UpdateNicknameRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserService {

    @GET("api/v1/users/me")
    suspend fun getUserInfo(): BaseModel<UserInfoResponse>

    @PUT("api/v1/users/me/nickname")
    suspend fun updateNickname(
        @Body updateNicknameRequest: UpdateNicknameRequest
    ): BaseModel<UserInfoResponse>
}