package com.project.kbong

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.project.presentation.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KbongApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}