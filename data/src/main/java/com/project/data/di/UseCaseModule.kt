package com.project.data.di

import com.project.domain.repository.calendar.CalendarRepository
import com.project.domain.usecase.GetCalendarHistoryGameUseCase
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


}
