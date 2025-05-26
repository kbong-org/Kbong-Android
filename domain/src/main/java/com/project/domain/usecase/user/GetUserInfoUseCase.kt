package com.project.domain.usecase.user

import com.project.domain.model.BaseModelContent
import com.project.domain.model.user.UserInfoContent
import com.project.domain.repository.UserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): BaseModelContent<UserInfoContent> {
        return userRepository.getUserInfo()
    }
}