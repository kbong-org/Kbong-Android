package com.project.presentation.home

import androidx.lifecycle.viewModelScope
import com.project.domain.usecase.GetCalenderHistoryGameUseCase
import com.project.presentation.mvi.BaseViewModel
import javax.inject.Inject
import com.project.presentation.home.HomeViewContract.*
import kotlinx.coroutines.launch

class HomeViewModel @Inject constructor(
    private val getCalenderHistoryGameUseCase: GetCalenderHistoryGameUseCase
) : BaseViewModel<HomeViewState, HomeViewEvent, HomeViewSideEffect>() {

    override fun createInitialState(): HomeViewState {
        return HomeViewState()
    }

    override suspend fun handleEvent(event: HomeViewEvent) {
        when (event) {
            else -> Unit
        }
    }

    private fun errorReduce() {
        reduce { copy(isError = true) }
    }

    fun getCalenderHistoryGame(year: Int, month: Int) {
        viewModelScope.launch {
            runCatching {
                getCalenderHistoryGameUseCase(year, month)
            }.onSuccess { response ->
                if (response.isSuccess) {
                    response.data?.let { data ->
                        reduce { copy(historyDaysContent = data) }
                    }
                } else {
                    errorReduce()
                }
            }.onFailure {
                errorReduce()
            }
        }
    }

}