package com.project.domain.model

data class BaseModelContent<T>(
    val isSuccess: Boolean = false,
    val data: T? = null,
    val errorResponse: BaseErrorContent = BaseErrorContent(),
)

data class BaseErrorContent(
    val code: String = "",
    val message: String = "",
)
