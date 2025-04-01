package com.project.data

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController

// todo 추후 Common 모듈 생성 후 이동 해야함
val LocalNavController = compositionLocalOf<NavHostController> {
    error("NavController not provided")
}
val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner) {
    "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
}