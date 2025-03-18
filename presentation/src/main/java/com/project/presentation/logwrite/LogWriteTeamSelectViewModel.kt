package com.project.presentation.logwrite

import com.project.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogWriteTeamSelectViewModel @Inject constructor(

) : BaseViewModel<LogWriteTeamSelectContract.LogWriteTeamSelectState, LogWriteTeamSelectContract.LogWriteTeamSelectEvent, LogWriteTeamSelectContract.LogWriteTeamSelectSideEffect>() {

    override fun createInitialState(): LogWriteTeamSelectContract.LogWriteTeamSelectState {
        return LogWriteTeamSelectContract.LogWriteTeamSelectState()
    }

    override suspend fun handleEvent(event: LogWriteTeamSelectContract.LogWriteTeamSelectEvent) {
        when (event) {

            else -> Unit
        }
    }
}