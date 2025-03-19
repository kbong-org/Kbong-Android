package com.project.data.model.user

import com.google.gson.annotations.SerializedName

data class MyTeamResponse(
    @SerializedName("code")
    val code: String = "",
    @SerializedName("fullName")
    val fullName: String = ""
)
