package com.project.presentation.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.project.domain.usecase.calendar.GetCalendarHistoryGameUseCase
import com.project.domain.usecase.calendar.GetDailyLogUseCase
import com.project.domain.usecase.calendar.GetGameLogUseCase
import com.project.presentation.home.HomeViewContract.HomeViewEvent
import com.project.presentation.home.HomeViewContract.HomeViewSideEffect
import com.project.presentation.home.HomeViewContract.HomeViewState
import com.project.presentation.mvi.BaseViewModel
import com.project.presentation.utils.stringToLocalDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCalendarHistoryGameUseCase: GetCalendarHistoryGameUseCase,
    private val getDailyLogUseCase: GetDailyLogUseCase,
    private val getGameLogUseCase: GetGameLogUseCase,
) : BaseViewModel<HomeViewState, HomeViewEvent, HomeViewSideEffect>() {

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        val currentDate = state.value.currentDate
        getCalendarHistoryGame(currentDate.year, currentDate.monthValue)
        getDailyLog(currentDate.year, currentDate.monthValue, currentDate.dayOfMonth)
        getGameCalendar(currentDate.year, currentDate.monthValue)
    }

    override fun createInitialState(): HomeViewState {
        return HomeViewState()
    }

    override suspend fun handleEvent(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.OnSelectedDate -> {
                updateSelectedDate(event.onSelectedDate)
            }

            is HomeViewEvent.OnTabSelected -> {
                updateSelectedTab(event.selectTab)
            }

            HomeViewEvent.OnClickAddHistory -> {
                // postSideEffect()
            }

            HomeViewEvent.OnClickMonth -> {
                postSideEffect(HomeViewSideEffect.ShowDatePicker)
            }
        }
    }

    private fun errorReduce() {
        reduce { copy(isError = true) }
    }

    private fun updateSelectedDate(selectedDate: String) {
        val beforeMonth = state.value.selectedDate.monthValue
        reduce { copy(selectedDate = selectedDate.stringToLocalDate()) }
        with(state.value.selectedDate) {
            getDailyLog(
                year = year,
                month = monthValue,
                day = dayOfMonth
            )
            // 선택한 달이 전에 선택한 달과 다를때
            if (beforeMonth != monthValue) {
                getCalendarHistoryGame(
                    year = year,
                    month = monthValue
                )
            }
        }

    }

    private fun updateSelectedTab(selectedTab: String) {
        reduce { copy(selectTab = selectedTab) }
    }


    fun getCalendarHistoryGame(year: Int, month: Int) {
        viewModelScope.launch {
            runCatching {
                getCalendarHistoryGameUseCase(year, month)
            }.onSuccess { response ->
                if (response.isSuccess) {
                    response.data?.let { data ->
                        reduce { copy(historyDayContents = data.historyDayListContent) }
                    }
                } else {
                    Log.e(TAG, "getCalendarHistoryGame else : ${response.errorResponse}")
                    errorReduce()
                }
            }.onFailure {
                Log.e(TAG, "getCalendarHistoryGame: ${it.message}")
                errorReduce()
            }
        }
    }

    fun getDailyLog(year: Int, month: Int, day: Int) {
        viewModelScope.launch {
            runCatching {
                getDailyLogUseCase(year, month, day)
            }.onSuccess { response ->
                if (response.isSuccess) {
                    response.data?.let { data ->
                        reduce { copy(dailyLogList = data.dailyLogList) }
                    }
                } else {
                    Log.e(TAG, "getDailyLog else : ${response.errorResponse}")
                    errorReduce()
                }
            }.onFailure {
                Log.e(TAG, "getDailyLog: ${it.message}")
                errorReduce()
            }
        }
    }


    // 월별 경기 일정 가져오기
    private fun getGameCalendar(year: Int, monthValue: Int) {
        viewModelScope.launch {
            runCatching {
                getGameLogUseCase(year, monthValue)
            }.onSuccess { response ->
                if (response.isSuccess) {
                    response.data?.let { data ->
                        reduce {
                            copy(
                                myTeamDisplayName = data.myTeamDisplayName,
                                gameDayContents = data.gameDayContent
                            )
                        }
                    }
                } else {
                    Log.e(TAG, "getGameCalendar else : ${response.errorResponse}")
                    errorReduce()
                }
            }.onFailure {
                Log.e(TAG, "getGameCalendar Error ${it.message}")
                errorReduce()
            }
        }
    }


}