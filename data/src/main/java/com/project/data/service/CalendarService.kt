package com.project.data.service

import com.project.data.model.BaseModel
import com.project.data.model.calendar.GameDayListResponse
import com.project.data.model.calendar.HistoryDayListResponse
import com.project.data.model.day.DailyLogListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CalendarService {

    @GET("api/v1/calendar/daily-log/{year}/{month}")
    suspend fun getCalendarHistoryGame(
        @Path("year") year: Int,
        @Path("month") month: Int
    ): BaseModel<HistoryDayListResponse>

    @GET("api/v1/calendar/daily-log/{year}/{month}/{day}")
    suspend fun getDailyLog(
        @Path("year") year: Int,
        @Path("month") month: Int,
        @Path("day") day: Int
    ): BaseModel<DailyLogListResponse>

    @GET("api/v1/calendar/game/{year}/{month}")
    suspend fun getGameList(
        @Path("year") year: Int,
        @Path("month") month: Int
    ): BaseModel<GameDayListResponse>
}