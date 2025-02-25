package com.project.presentation.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.project.domain.usecase.GetCalenderHistoryGameUseCase
import com.project.domain.usecase.GetDailyLogUseCase
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
    private val getCalenderHistoryGameUseCase: GetCalenderHistoryGameUseCase,
    private val getDailyLogUseCase: GetDailyLogUseCase,
) : BaseViewModel<HomeViewState, HomeViewEvent, HomeViewSideEffect>() {


    init {
        getCalenderHistoryGame(
            year = state.value.currentDate.year,
            month = state.value.currentDate.monthValue
            /* year = 2024,
             month = 6*/
        )

        getDailyLog(
            year = state.value.currentDate.year,
            month = state.value.currentDate.monthValue,
            day = state.value.currentDate.dayOfMonth
        )
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
        }
    }

    private fun errorReduce() {
        reduce { copy(isError = true) }
    }

    private fun updateSelectedDate(selectedDate: String) {
        reduce { copy(selectedDate = selectedDate.stringToLocalDate()) }
    }

    private fun updateSelectedTab(selectedTab: String) {
        reduce { copy(selectTab = selectedTab) }
    }


    fun getCalenderHistoryGame(year: Int, month: Int) {
        viewModelScope.launch {
            runCatching {
                getCalenderHistoryGameUseCase(year, month)
            }.onSuccess { response ->
                if (response.isSuccess) {
                    response.data?.let { data ->
                        reduce { copy(historyDayContents = data.historyDayListContent) }
                    }
                } else {
                    Log.e(TAG, "getCalenderHistoryGame else : ${response.errorResponse}")
                    errorReduce()
                }
            }.onFailure {
                Log.e(TAG, "getCalenderHistoryGame: ${it.message}")
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

}