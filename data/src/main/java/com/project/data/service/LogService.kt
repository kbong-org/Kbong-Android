package com.project.data.service

import com.project.data.model.BaseModel
import com.project.data.model.log.ChoiceLogRequestDto
import com.project.data.model.log.DailyLogDetailResponseDto
import com.project.data.model.log.FreeLogRequestDto
import com.project.data.model.log.GameListForLogResponse
import com.project.data.model.log.LogUploadResponseDto
import com.project.data.model.log.PresignedUrlDto
import com.project.data.model.log.ShortLogRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LogService {
    @GET("/api/v1/daily-log/game")
    suspend fun getGamesForLog(@Query("date") date: String): BaseModel<GameListForLogResponse>
    @POST("/api/v1/daily-log/free")
    suspend fun postFreeLog(@Body request: FreeLogRequestDto): BaseModel<LogUploadResponseDto>
    @GET("/api/v1/files/upload")
    suspend fun getPresignedUrl(@Query("extension") extension: String): BaseModel<PresignedUrlDto>
    @POST("/api/v1/daily-log/short")
    suspend fun postShortLog(@Body request: ShortLogRequestDto): BaseModel<LogUploadResponseDto>
    @POST("/api/v1/daily-log/choice")
    suspend fun postChoiceLog(@Body request: ChoiceLogRequestDto): BaseModel<LogUploadResponseDto>
    @GET("/api/v1/daily-log/{id}")
    suspend fun getDailyLogDetail(@Path("id") id: Long): BaseModel<DailyLogDetailResponseDto>
    @DELETE("/api/v1/daily-log/{id}")
    suspend fun deleteDailyLog(@Path("id") id: Long): BaseModel<Unit>
}