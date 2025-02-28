package com.project.data

import com.project.data.model.LoginResponseData
import com.project.data.model.TokenResponse
import com.project.domain.model.Token
import com.project.domain.model.User

fun LoginResponseData.toDomainUser(): User =
    User(
        id = this.userId,
        nickname = this.nickname,
        accessToken = this.accessToken,
        refreshToken = this.refreshToken
    )

fun TokenResponse.toDomainToken(): Token =
    Token(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken
    )