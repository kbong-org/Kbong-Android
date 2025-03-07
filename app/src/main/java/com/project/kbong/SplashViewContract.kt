package com.project.kbong

import com.project.presentation.mvi.UiEvent
import com.project.presentation.mvi.UiSideEffect
import com.project.presentation.mvi.UiState


class SplashViewContract {

    /**
     * 현재 화면에 필요한 상태들을 모아둔다.
     */
    data class SplashViewState(
        val isToken: Boolean = false
    ) : UiState

    /**
     * 액션 정의
     */
    sealed interface SplashViewEvent : UiEvent {

    }

    /**
     * SideEffect로 발생되는 이벤트를 정의
     */
    sealed interface SplashViewSideEffect : UiSideEffect {

    }
}