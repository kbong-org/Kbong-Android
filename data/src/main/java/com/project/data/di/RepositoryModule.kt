package com.project.data.di

import com.project.data.local.repositoryimpl.user.UserDataStoreRepositoryImpl
import com.project.data.repository.AuthRepositoryImpl
import com.project.data.repositoryimpl.calendar.CalendarRepositoryImpl
import com.project.data.repositoryimpl.user.UserRepositoryImpl
import com.project.data.repositoryimpl.log.DayRepositoryImpl
import com.project.data.repositoryimpl.log.LogRepositoryImpl
import com.project.data.repositoryimpl.question.QuestionRepositoryImpl
import com.project.domain.repository.AuthRepository
import com.project.domain.repository.UserRepository
import com.project.domain.repository.DayRepository
import com.project.domain.repository.calendar.CalendarRepository
import com.project.domain.repository.local.user.UserDataStoreRepository
import com.project.domain.repository.log.LogRepository
import com.project.domain.repository.question.QuestionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideCalendarRepository(
        calendarRepositoryImpl: CalendarRepositoryImpl,
    ): CalendarRepository

    @Binds
    abstract fun provideAuthRepository(
        authRepository: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun provideUserDataStoreRepository(
        userDataStoreRepositoryImpl: UserDataStoreRepositoryImpl
    ): UserDataStoreRepository

    @Binds
    abstract fun provideDayRepository(
        dayRepositoryImpl: DayRepositoryImpl
    ): DayRepository

    @Binds
    abstract fun provideLogRepository(
        impl: LogRepositoryImpl
    ): LogRepository

    @Binds
    abstract fun bindQuestionRepository(
        impl: QuestionRepositoryImpl
    ): QuestionRepository
    @Binds
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

}
