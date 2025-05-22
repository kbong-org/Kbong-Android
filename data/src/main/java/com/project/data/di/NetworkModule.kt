package com.project.data.di


import com.project.data.remote.AuthService
import com.project.data.service.CalendarService
import com.project.data.service.LogService
import com.project.data.service.QuestionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCalendarService(
        retrofit: Retrofit,
    ): CalendarService {
        return retrofit.create(CalendarService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideLogService(retrofit: Retrofit): LogService {
        return retrofit.create(LogService::class.java)
    }

    @Provides
    @Singleton
    fun provideQuestionService(retrofit: Retrofit): QuestionService {
        return retrofit.create(QuestionService::class.java)
    }
}
