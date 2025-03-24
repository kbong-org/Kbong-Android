package com.project.presentation.my

import com.project.domain.model.user.UserInfoContent
import com.project.presentation.mvi.UiEvent
import com.project.presentation.mvi.UiSideEffect
import com.project.presentation.mvi.UiState
import com.project.presentation.my.ui.CATALOG

class MyContract {

    /**
     * 현재 화면에 필요한 상태들을 모아둔다.
     */
    data class MyViewState(
        val isError: Boolean = false,
        val userInfoContent: UserInfoContent = UserInfoContent(),
        val selectViewType: String = CATALOG
    ) : UiState

    /**
     * 액션 정의
     */
    sealed interface MyViewEvent : UiEvent {
        data class OnClickSelectViewType(val type: String) : MyViewEvent
    }

    /**
     * SideEffect로 발생되는 이벤트를 정의
     */
    sealed interface MyViewSideEffect : UiSideEffect {

    }
}