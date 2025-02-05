package com.project.presentation.home

import com.project.domain.model.calender.HistoryDaysContent
import com.project.presentation.mvi.UiEvent
import com.project.presentation.mvi.UiSideEffect
import com.project.presentation.mvi.UiState

class HomeViewContract {

    /**
     * 현재 화면에 필요한 상태들을 모아둔다.
     */
    data class HomeViewState(
        val isError: Boolean = false,
        val historyDaysContent: List<HistoryDaysContent> = listOf(HistoryDaysContent())

    ) : UiState

    /**
     * 액션 정의
     */
    sealed interface HomeViewEvent : UiEvent {

    }

    /**
     * SideEffect로 발생되는 이벤트를 정의
     */
    sealed interface HomeViewSideEffect : UiSideEffect {

    }
}