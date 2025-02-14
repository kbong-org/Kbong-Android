package com.project.data.service

import com.project.data.model.BaseModel
import com.project.data.model.calender.HistoryDayListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CalenderService {

    @GET("api/v1/calendar/daily-log/{year}/{month}")
    suspend fun getCalenderHistoryGame(
        @Path("year") year: Int,
        @Path("month") month: Int
    ): BaseModel<HistoryDayListResponse>
}