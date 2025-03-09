package com.project.kbong.splash

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.project.domain.usecase.user.GetUserTokenUseCase
import com.project.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserTokenUseCase: GetUserTokenUseCase
) : BaseViewModel<SplashViewContract.SplashViewState, SplashViewContract.SplashViewEvent, SplashViewContract.SplashViewSideEffect>() {

    init {
        getUserToken()
    }

    override fun createInitialState(): SplashViewContract.SplashViewState {
        return SplashViewContract.SplashViewState()
    }

    override suspend fun handleEvent(event: SplashViewContract.SplashViewEvent) {
        when (event) {
            else -> Unit
        }
    }

    private fun getUserToken() {
        viewModelScope.launch {
            runCatching {
                getUserTokenUseCase()
            }.onSuccess { token ->
                val isToken = token.accessToken.isNotEmpty()
                reduce {
                    copy(isToken = isToken)
                }
            }.onFailure {
                Log.e(TAG, "getUserToken: ${it.message}")
            }
        }
    }
}