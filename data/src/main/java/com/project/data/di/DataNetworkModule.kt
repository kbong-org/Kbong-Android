package com.project.data.di

import android.util.Log
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataNetworkModule {

    @Singleton
    @Provides
    fun provideNormalRetrofitService(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://ec2-52-78-105-177.ap-northeast-2.compute.amazonaws.com/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(40, TimeUnit.SECONDS)
            readTimeout(40, TimeUnit.SECONDS)
            writeTimeout(40, TimeUnit.SECONDS)
            addInterceptor(httpLoggingInterceptor)
            addInterceptor { chain ->
                try {
                    val newRequest = chain.request().newBuilder().apply {
                        addHeader(
                            "Authorization",
                            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI4IiwidXNlci1pbmZvIjp7ImlkIjo4LCJuaWNrbmFtZSI6IuyngOqyjCIsInJvbGVzIjpbIk1FTUJFUiJdfSwiaWF0IjoxNzQwODI5MzkyLCJleHAiOjE3NDEwODg1OTJ9.dHT8GFcjxLR30ogMyM8C1plG12OoT8Iex82dVd_8pSc"
                        )
                    }.build()
                    chain.proceed(newRequest)
                } catch (e: Exception) {
                    Log.e("OkHttp", "Error in interceptor", e)
                    chain.proceed(chain.request())
                }
            }
        }.build()
    }
}
