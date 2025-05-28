package com.project.presentation.my

import com.project.domain.model.user.UserInfoContent
import com.project.presentation.mvi.UiEvent
import com.project.presentation.mvi.UiSideEffect
import com.project.presentation.mvi.UiState
import com.project.presentation.my.ui.LIST
import com.project.presentation.setting.edit.ProfileEditType

class MyContract {

    /**
     * 현재 화면에 필요한 상태들을 모아둔다.
     */
    data class MyViewState(
        val isError: Boolean = false,
        val userInfoContent: UserInfoContent = UserInfoContent(),
        val selectViewType: String = LIST,
        val myTeamType: MyTeamType = MyTeamType.fromTypeData(userInfoContent.myTeam.fullName),
        val newNickname: String = "",
        val snackbarMessage: String = ""
    ) : UiState

    /**
     * 액션 정의
     */
    sealed interface MyViewEvent : UiEvent {
        data class OnClickSelectViewType(val type: String) : MyViewEvent
        data object OnClickSetting : MyViewEvent
        data object OnClickBack : MyViewEvent


        sealed interface SettingEvent : UiEvent {
            data object OnClickProfileEdit : MyViewEvent
        }

        sealed interface ProfileEditEvent : UiEvent {
            data class OnClickEditMenu(val type: ProfileEditType) : MyViewEvent
            data class OnChangedNickname(val newNickname: String) : MyViewEvent
            data class OnClickNicknameSave(val snackbarMessage: String) : MyViewEvent
        }
    }

    /**
     * SideEffect로 발생되는 이벤트를 정의
     */
    sealed interface MyViewSideEffect : UiSideEffect {
        data object NavigateToBack : MyViewSideEffect
        data object NavigateToSetting : MyViewSideEffect
        data object NavigateToProfileEdit : MyViewSideEffect
        data class ChangeProfileEditType(val type: ProfileEditType) : MyViewSideEffect
        data object ShowSnackbar : MyViewSideEffect
    }
}