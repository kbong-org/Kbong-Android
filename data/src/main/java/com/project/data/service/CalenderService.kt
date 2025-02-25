package com.project.data.service

import com.project.data.model.BaseModel
import com.project.data.model.calender.HistoryDayListResponse
import com.project.data.model.day.DailyLogListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CalenderService {

    // 실 서버 api/v1/calendar/daily-log/{year}/{month}
    @GET("api/v1/calendar-sample/daily-log/{year}/{month}")
    suspend fun getCalenderHistoryGame(
        @Path("year") year: Int,
        @Path("month") month: Int
    ): BaseModel<HistoryDayListResponse>

    // 실 서버 api/v1/calendar/daily-log/{year}/{month}/{day}
    @GET("api/v1/calendar-sample/daily-log/{year}/{month}/{day}")
    suspend fun getDailyLog(
        @Path("year") year: Int,
        @Path("month") month: Int,
        @Path("day") day: Int
    ): BaseModel<DailyLogListResponse>
}