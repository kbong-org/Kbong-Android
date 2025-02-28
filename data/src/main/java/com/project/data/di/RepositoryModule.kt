package com.project.data.di

import com.project.data.repository.AuthRepositoryImpl
import com.project.data.repositoryimpl.calender.CalenderRepositoryImpl
import com.project.domain.repository.AuthRepository
import com.project.domain.repository.calender.CalenderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideCalenderRepository(
        calenderRepositoryImpl: CalenderRepositoryImpl,
    ): CalenderRepository

    @Binds
    abstract fun provideAuthRepository(
        authRepository: AuthRepositoryImpl
    ): AuthRepository

}
