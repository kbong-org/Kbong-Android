package com.project.presentation.home

import com.project.domain.model.calender.HistoryDayContent
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
      //  val currentDate: LocalDate = LocalDate.now(),
       // val selectedDate: LocalDate = LocalDate.now(),
        val selectTab: String = "직관기록",
        val currentDate: LocalDate = LocalDate.of(2024,6,1),
        val selectedDate: LocalDate = LocalDate.of(2024, 6, 21),
        val historyDayContents: List<HistoryDayContent> = listOf(HistoryDayContent()),
        val firstDayOfWeek: Int = currentDate.dayOfWeek.value,
        val dailyLogList: List<DailyLog> = listOf(
      /*      DailyLog(
                id = 7243,
                awayTeamDisplayName = "KT",
                homeTeamDisplayName = "삼성",
                stadiumFullName = "수원 KT위즈파크",
                emotion = "HAPPY",
                type = "CHOICE",
                imageList = listOf()
            ),
            DailyLog(
                id = 723,
                awayTeamDisplayName = "랜더스",
                homeTeamDisplayName = "삼성",
                stadiumFullName = "랜더스 필드",
                emotion = "HAPPY",
                type = "CHOICE",
                imageList = listOf()
            ),
            DailyLog(
                id = 723,
                awayTeamDisplayName = "기아",
                homeTeamDisplayName = "두산",
                stadiumFullName = "잠실 야구장",
                emotion = "HAPPY",
                type = "CHOICE",
                imageList = listOf()
            )*/
        )
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