package com.project.data.mapper

import com.project.data.model.BaseErrorModel
import com.project.data.model.BaseModel
import com.project.domain.model.BaseErrorContent
import com.project.domain.model.BaseModelContent

fun BaseErrorModel?.toDomain(): BaseErrorContent {
    return if (this == null) {
        BaseErrorContent()
    } else {
        BaseErrorContent(
            code = code,
            message = message
        )
    }
}

fun <T, R> BaseModel<T>.toBaseDomain(transform: (T) -> R): BaseModelContent<R> {
    return BaseModelContent(
        isSuccess = isSuccess,
        data = this.data?.let { transform(it) },
        errorResponse = errorResponse.toDomain(),
    )
}