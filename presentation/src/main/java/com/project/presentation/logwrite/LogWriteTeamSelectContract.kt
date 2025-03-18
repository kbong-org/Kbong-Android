package com.project.presentation.logwrite

import com.project.presentation.mvi.UiEvent
import com.project.presentation.mvi.UiSideEffect
import com.project.presentation.mvi.UiState

class LogWriteTeamSelectContract {

    /**
     * 현재 화면에 필요한 상태들을 모아둔다.
     */
    data class LogWriteTeamSelectState(
        val temp: String = ""
    ) : UiState

    /**
     * 액션 정의
     */
    sealed interface LogWriteTeamSelectEvent : UiEvent {

    }

    /**
     * SideEffect로 발생되는 이벤트를 정의
     */
    sealed interface LogWriteTeamSelectSideEffect : UiSideEffect {

    }
}