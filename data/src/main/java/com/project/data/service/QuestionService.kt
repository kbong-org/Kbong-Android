package com.project.data.service

import com.project.data.model.BaseModel
import com.project.data.model.question.ChoiceQuestionListDto
import com.project.data.model.question.ShortQuestionDto
import com.project.domain.model.question.ChoiceQuestion
import retrofit2.http.GET

interface QuestionService {

    @GET("/api/v1/daily-log/question/short")
    suspend fun getShortQuestion(): BaseModel<ShortQuestionDto>

    @GET("/api/v1/daily-log/question/choice")
    suspend fun getChoiceQuestions(): BaseModel<ChoiceQuestionListDto>

    @GET("/api/v1/daily-log/question/choice/refresh")
    suspend fun getRefreshedChoiceQuestion(): BaseModel<ChoiceQuestion>
}