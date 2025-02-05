package com.project.data.mapper

import com.project.data.model.BaseErrorModel
import com.project.domain.model.BaseErrorContent

fun BaseErrorModel.toDataModel(): BaseErrorContent{
    return BaseErrorContent(
        code = code,
        message = message
    )
}