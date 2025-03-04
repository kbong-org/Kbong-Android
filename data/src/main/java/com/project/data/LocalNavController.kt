package com.project.data

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

// todo 추후 Common 모듈 생성 후 이동 해야함
val LocalNavController = compositionLocalOf<NavHostController> {
    error("NavController not provided")
}
