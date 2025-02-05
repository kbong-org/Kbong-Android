package com.project.data.di

import com.project.domain.repository.calender.CalenderRepository
import com.project.domain.usecase.GetCalenderHistoryGameUseCase
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
        calenderRepository: CalenderRepository,
    ): GetCalenderHistoryGameUseCase {
        return GetCalenderHistoryGameUseCase(
            calenderRepository = calenderRepository,
        )
    }


}
