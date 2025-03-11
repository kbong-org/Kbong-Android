package com.project.data.di

import android.content.Context
import com.project.data.local.datastore.KBongDataStore
import com.project.data.local.datastore.KBongDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideKBongDataStoreImpl(
        @ApplicationContext context: Context,
    ): KBongDataStore {
        return KBongDataStoreImpl(context)
    }
}