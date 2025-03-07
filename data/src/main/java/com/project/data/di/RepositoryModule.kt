package com.project.data.di

import com.project.data.repository.AuthRepositoryImpl
import com.project.data.repositoryimpl.calendar.CalendarRepositoryImpl
import com.project.data.repositoryimpl.user.UserDataStoreRepositoryImpl
import com.project.domain.repository.AuthRepository
import com.project.domain.repository.calendar.CalendarRepository
import com.project.domain.repository.user.UserDataStoreRepository
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

}
