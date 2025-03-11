package com.project.data.di


import com.project.data.datasource.AuthRemoteDataSource
import com.project.data.datasource.AuthRemoteDataSourceImpl
import com.project.data.datasource.calendar.CalendarDataSource
import com.project.data.datasource.calendar.CalendarDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideCalendarDataSource(
        calendarDataSourceImpl: CalendarDataSourceImpl,
    ): CalendarDataSource

    @Binds
    abstract fun provideAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource
}
