package com.project.presentation.home

import com.project.domain.model.calender.HistoryDayContent
import com.project.presentation.mvi.UiEvent
import com.project.presentation.mvi.UiSideEffect
import com.project.presentation.mvi.UiState
import java.time.LocalDate


class HomeViewContract {

    /**
     * 현재 화면에 필요한 상태들을 모아둔다.
     */
    data class HomeViewState(
        val isError: Boolean = false,
      //  val currentDate: LocalDate = LocalDate.now(),
       // val selectedDate: LocalDate = LocalDate.now(),
        val selectTab: String = "직관기록",
        val currentDate: LocalDate = LocalDate.of(2024,6,1),
        val selectedDate: LocalDate = LocalDate.of(2024, 6, 21),
        val historyDayContents: List<HistoryDayContent> = listOf(HistoryDayContent()),
        val firstDayOfWeek: Int = currentDate.dayOfWeek.value
    ) : UiState

    /**
     * 액션 정의
     */
    sealed interface HomeViewEvent : UiEvent {
        data class OnSelectedDate(val onSelectedDate: String) : HomeViewEvent
        data class OnTabSelected(val selectTab: String) : HomeViewEvent
        data object OnClickAddHistory : HomeViewEvent
    }

    /**
     * SideEffect로 발생되는 이벤트를 정의
     */
    sealed interface HomeViewSideEffect : UiSideEffect {

    }
}