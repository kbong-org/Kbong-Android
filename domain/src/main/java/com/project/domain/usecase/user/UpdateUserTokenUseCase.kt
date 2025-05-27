package com.project.domain.usecase.user

import com.project.domain.model.user.TokenData
import com.project.domain.repository.user.UserDataStoreRepository
import javax.inject.Inject

class UpdateUserTokenUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository
) {
    suspend operator fun invoke(
        userTokenData: TokenData
    ) {
        userDataStoreRepository.updateUserToken(userTokenData)
    }
}