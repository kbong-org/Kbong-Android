package com.project.domain.usecase.user

import com.project.domain.model.BaseModelContent
import com.project.domain.model.user.UserInfoContent
import com.project.domain.repository.UserRepository
import javax.inject.Inject

class UpdateNicknameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(nickname: String): BaseModelContent<UserInfoContent> {
        return userRepository.updateNickname(nickname)
    }
}