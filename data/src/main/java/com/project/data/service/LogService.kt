package com.project.data.service

import com.project.data.model.BaseModel
import com.project.data.model.log.GameListForLogResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LogService {
    @GET("/api/v1/daily-log/game")
    suspend fun getGamesForLog(@Query("date") date: String): BaseModel<GameListForLogResponse>
}