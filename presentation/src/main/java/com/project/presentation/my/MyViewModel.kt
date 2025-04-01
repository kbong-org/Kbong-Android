package com.project.presentation.my

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.project.domain.usecase.user.GetUserInfoUseCase
import com.project.domain.usecase.user.UpdateNicknameUseCase
import com.project.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val updateNicknameUseCase: UpdateNicknameUseCase,
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
            MyContract.MyViewEvent.OnClickSetting -> postSideEffect(MyContract.MyViewSideEffect.NavigateToSetting)
            MyContract.MyViewEvent.OnClickBack -> postSideEffect(MyContract.MyViewSideEffect.NavigateToBack)
            MyContract.MyViewEvent.SettingEvent.OnClickProfileEdit ->
                postSideEffect(MyContract.MyViewSideEffect.NavigateToProfileEdit)

            is MyContract.MyViewEvent.ProfileEditEvent.OnClickEditMenu -> postSideEffect(
                MyContract.MyViewSideEffect.ChangeProfileEditType(event.type)
            )

            is MyContract.MyViewEvent.ProfileEditEvent.OnChangedNickname -> updateNicknameState(
                event.nickname
            )

            is MyContract.MyViewEvent.ProfileEditEvent.OnClickNicknameSave -> {
                updateNickname(event.snackbarMessage)
            }

            else -> Unit
        }
    }

    private fun errorReduce(errorMessage: String) {
        reduce { copy(isError = true) }
        updateSnackbar(errorMessage)
    }

    private fun getUserInfoData() {
        viewModelScope.launch {
            runCatching {
                getUserInfoUseCase()
            }.onSuccess { response ->
                if (response.isSuccess) {
                    response.data?.let {
                        reduce {
                            copy(
                                userInfoContent = it,
                                myTeamType = MyTeamType.fromTypeData(it.myTeam.fullName)
                            )
                        }
                    }
                } else {
                    Log.e(TAG, "getUserInfoData else : ${response.errorResponse}")
                    errorReduce(response.errorResponse.message)
                }
            }.onFailure {
                Log.e(TAG, "getUserInfoData: ${it.message}")
                errorReduce(it.message?:"네트워크 에러")
            }
        }
    }


    private fun updateSelectViewType(type: String) {
        reduce { copy(selectViewType = type) }
    }

    private fun updateNicknameState(nickName: String) {
        reduce { copy(nickname = nickName) }
    }

    private fun updateSnackbar(message: String) {
        reduce { copy(snackbarMessage = message) }
        postSideEffect(MyContract.MyViewSideEffect.ShowSnackbar)
    }


    private fun updateNickname(snackbarMessage: String) {
        viewModelScope.launch {
            runCatching {
                updateNicknameUseCase(state.value.nickname)
            }.onSuccess { response ->
                if (response.isSuccess) {
                    response.data?.let {
                        updateSnackbar(snackbarMessage)
                    }
                    postSideEffect(MyContract.MyViewSideEffect.NavigateToBack)
                } else {
                    Log.e(TAG, "updateNickname else : ${response.errorResponse}")
                    errorReduce(response.errorResponse.message)
                }
            }.onFailure {
                Log.e(TAG, "updateNickname: ${it.message}")
                errorReduce(it.message?:"네트워크 에러")
            }
        }
    }
}