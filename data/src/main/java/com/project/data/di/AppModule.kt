package com.project.data.di

/*
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "http://ec2-52-78-105-177.ap-northeast-2.compute.amazonaws.com"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(authService: AuthService): AuthRemoteDataSource =
        AuthRemoteDataSource(authService)

    @Provides
    @Singleton
    fun provideAuthRepository(remoteDataSource: AuthRemoteDataSource): AuthRepository =
        AuthRepositoryImpl(remoteDataSource)

    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase =
        LoginUseCase(authRepository)

    @Provides
    fun provideRefreshTokenUseCase(authRepository: AuthRepository): RefreshTokenUseCase =
        RefreshTokenUseCase(authRepository)

    @Provides
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpUseCase =
        SignUpUseCase(authRepository)
}*/
