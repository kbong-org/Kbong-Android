package com.project.data.di

import com.project.domain.repository.calendar.CalendarRepository
import com.project.domain.repository.user.UserDataStoreRepository
import com.project.domain.usecase.calendar.GetCalendarHistoryGameUseCase
import com.project.domain.usecase.user.GetUserTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideUpdateUserDataStoreUseCase(
        calendarRepository: CalendarRepository,
    ): GetCalendarHistoryGameUseCase {
        return GetCalendarHistoryGameUseCase(
            calendarRepository = calendarRepository,
        )
    }

    @Singleton
    @Provides
    fun provideGetUserTokenUseCase(
        userDataStoreRepository: UserDataStoreRepository,
    ): GetUserTokenUseCase {
        return GetUserTokenUseCase(
            userDataStoreRepository = userDataStoreRepository,
        )
    }

}
