package com.project.presentation.my

import com.project.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(

) : BaseViewModel<MyContract.MyViewState, MyContract.MyViewEvent, MyContract.MyViewSideEffect>() {

    override fun createInitialState(): MyContract.MyViewState {
        return MyContract.MyViewState()
    }

    override suspend fun handleEvent(event: MyContract.MyViewEvent) {
        when (event) {
            else -> Unit
        }
    }
}