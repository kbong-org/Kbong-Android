package com.project.data.service

import com.project.data.model.BaseModel
import com.project.data.model.user.UserInfoResponse
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface UserService {

    @GET("api/v1/users/me")
    suspend fun getUserInfo(): BaseModel<UserInfoResponse>

    @PUT("api/v1/users/me/nickname")
    suspend fun updateNickname(
        @Query("nickname") nickname: String
    ): BaseModel<UserInfoResponse>
}