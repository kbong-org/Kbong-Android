package com.project.presentation.my

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.project.domain.usecase.user.GetUserInfoUseCase
import com.project.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : BaseViewModel<MyContract.MyViewState, MyContract.MyViewEvent, MyContract.MyViewSideEffect>() {

    init {
        getUserInfoData()
    }

    override fun createInitialState(): MyContract.MyViewState {
        return MyContract.MyViewState()
    }

    override suspend fun handleEvent(event: MyContract.MyViewEvent) {
        when (event) {
            is MyContract.MyViewEvent.OnClickSelectViewType -> updateSelectViewType(event.type)
            else -> Unit
        }
    }

    private fun errorReduce() {
        reduce { copy(isError = true) }
    }

    private fun getUserInfoData() {
        viewModelScope.launch {
            runCatching {
                getUserInfoUseCase()
            }.onSuccess { response ->
                if (response.isSuccess) {
                    response.data?.let {
                        reduce { copy(userInfoContent = it) }
                    }
                } else {
                    Log.e(TAG, "getUserInfoData else : ${response.errorResponse}")
                    errorReduce()
                }
            }.onFailure {
                Log.e(TAG, "getUserInfoData: ${it.message}")
                errorReduce()
            }
        }
    }


    private fun updateSelectViewType(type: String) {
        reduce { copy(selectViewType = type) }
    }

}