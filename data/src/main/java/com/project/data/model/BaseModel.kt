package com.project.data.model

import com.google.gson.annotations.SerializedName

data class BaseModel<T>(
    @SerializedName("isSuccess")
    val isSuccess: Boolean = false,
    @SerializedName("data")
    val data: T? = null,
    @SerializedName("errorResponse")
    val errorResponse: BaseErrorModel = BaseErrorModel(),
)

data class BaseErrorModel(
    @SerializedName("code")
    val code: String = "",
    @SerializedName("message")
    val message: String = ""
)
