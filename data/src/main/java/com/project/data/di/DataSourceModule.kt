package com.project.data.di


import com.project.data.datasource.AuthRemoteDataSource
import com.project.data.datasource.AuthRemoteDataSourceImpl
import com.project.data.datasource.calender.CalenderDataSource
import com.project.data.datasource.calender.CalenderDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideCalenderDataSource(
        calenderDataSourceImpl: CalenderDataSourceImpl,
    ): CalenderDataSource

    @Binds
    abstract fun provideAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource
}
