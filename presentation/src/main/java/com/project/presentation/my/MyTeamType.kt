package com.project.presentation.my

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import com.project.kbong.designsystem.theme.KBongTeamTigers
import com.project.kbong.designsystem.theme.myPageBackgroundRed

enum class MyTeamType(
    val teamName: String,
    val backgroundColor: Color,
    val teamTagBackgroundColor: Color,
    val isBlueStadium: Boolean = true,
    val lottieFile: Drawable? = null,
) {
    /* "KIA 타이거즈" to "KIA",
     "두산 베어스" to "DOOSAN",
     "롯데 자이언츠" to "LOTTE",
     "삼성 라이온즈" to "SAMSUNG",
     "SSG 랜더스" to "SSG",
     "NC 다이노스" to "NC",
     "LG 트윈스" to "LG",
     "키움 히어로즈" to "KIWOOM",
     "KT 위즈" to "KT",
     "한화 이글즈" to "HANHWA",
     "모두 응원해요" to "NONE"*/
    KIA(
        teamName = "KIA 타이거즈",
        isBlueStadium = false,
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