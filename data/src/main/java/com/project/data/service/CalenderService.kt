package com.project.data.service

import com.project.data.model.BaseModel
import com.project.data.model.calender.HistoryDaysResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CalenderService {

    @GET("/api/v1/calendar/daily-log")
    suspend fun getCalenderHistoryGame(
        @Query("year") year: Int,
        @Query("month") month: Int
    ): BaseModel<List<HistoryDaysResponse>>
}