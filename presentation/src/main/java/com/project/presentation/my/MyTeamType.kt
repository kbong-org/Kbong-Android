package com.project.presentation.my

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import com.project.kbong.designsystem.theme.KBongTeamTigers
import com.project.kbong.designsystem.theme.myPageBackgroundRed

enum class MyTeamType(
    val teamName: String,
    val backgroundColor: Color,
    val teamTagBackgroundColor: Color,
    val lottieFile: Drawable? = null,
) {

    KIA(
        teamName = "KIA 타이거즈",
        backgroundColor = myPageBackgroundRed,
        teamTagBackgroundColor = KBongTeamTigers,
        lottieFile = null
    );

    companion object {
        fun fromTypeData(teamName: String): MyTeamType {
            return MyTeamType.entries.find { it.teamName == teamName } ?: KIA
        }
    }
}