package com.project.presentation.home

import com.project.domain.model.calendar.GameDayContent
import com.project.domain.model.calendar.HistoryDayContent
import com.project.domain.model.day.DailyGameLog
import com.project.domain.model.day.DailyLog
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
        val currentDate: LocalDate = LocalDate.now(),
        val selectedDate: LocalDate = LocalDate.now(),
        val selectTab: String = "직관기록",
        val historyDayContents: List<HistoryDayContent> = listOf(HistoryDayContent()),
        val dailyLogList: List<DailyLog> = listOf(),
        val dailyGameLogList: List<DailyGameLog> = listOf(),
        val gameDayContents: List<GameDayContent> = listOf(),
        val myTeamDisplayName: String = "",
    ) : UiState

    /**
     * 액션 정의
     */
    sealed interface HomeViewEvent : UiEvent {
        data class OnSelectedDate(val onSelectedDate: String) : HomeViewEvent
        data class OnTabSelected(val selectTab: String) : HomeViewEvent
        data object OnClickMonth : HomeViewEvent
        data object OnClickAddHistory : HomeViewEvent
    }

    /**
     * SideEffect로 발생되는 이벤트를 정의
     */
    sealed interface HomeViewSideEffect : UiSideEffect {
        data object ShowDatePicker : HomeViewSideEffect
        data class NavigateToSelectGame(val date: LocalDate) : HomeViewSideEffect
    }
}