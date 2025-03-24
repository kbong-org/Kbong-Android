package com.project.presentation.my

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongTeamBears
import com.project.kbong.designsystem.theme.KBongTeamEagles
import com.project.kbong.designsystem.theme.KBongTeamGiants
import com.project.kbong.designsystem.theme.KBongTeamGray10
import com.project.kbong.designsystem.theme.KBongTeamHeroes
import com.project.kbong.designsystem.theme.KBongTeamLions
import com.project.kbong.designsystem.theme.KBongTeamNc
import com.project.kbong.designsystem.theme.KBongTeamSsg
import com.project.kbong.designsystem.theme.KBongTeamTigers
import com.project.kbong.designsystem.theme.KBongTeamTwins
import com.project.kbong.designsystem.theme.myPageBackgroundBlue
import com.project.kbong.designsystem.theme.myPageBackgroundRed

enum class MyTeamType(
    val teamName: String,
    val backgroundColor: Color,
    val teamTagBackgroundColor: Color,
    val isBlueStadium: Boolean = true,
    val lottieFile: Drawable? = null,
) {
    DOOSAN(
        teamName = "두산 베어스",
        isBlueStadium = true,
        backgroundColor = myPageBackgroundBlue,
        teamTagBackgroundColor = KBongTeamBears,
        lottieFile = null
    ),

    LOTTE(
        teamName = "롯데 자이언츠",
        isBlueStadium = true,
        backgroundColor = myPageBackgroundBlue,
        teamTagBackgroundColor = KBongTeamGiants,
        lottieFile = null
    ),

    SAMSUNG(
        teamName = "삼성 라이온즈",
        isBlueStadium = true,
        backgroundColor = myPageBackgroundBlue,
        teamTagBackgroundColor = KBongTeamLions,
        lottieFile = null
    ),

    SSG(
        teamName = "SSG 랜더스",
        isBlueStadium = false,
        backgroundColor = myPageBackgroundRed,
        teamTagBackgroundColor = KBongTeamSsg,
        lottieFile = null
    ),

    NC(
        teamName = "NC 다이노스",
        isBlueStadium = true,
        backgroundColor = myPageBackgroundBlue,
        teamTagBackgroundColor = KBongTeamNc,
        lottieFile = null
    ),

    LG(
        teamName = "LG 트윈스",
        isBlueStadium = false,
        backgroundColor = myPageBackgroundRed,
        teamTagBackgroundColor = KBongTeamTwins,
        lottieFile = null
    ),

    KIWOOM(
        teamName = "키움 히어로즈",
        isBlueStadium = false,
        backgroundColor = myPageBackgroundRed,
        teamTagBackgroundColor = KBongTeamHeroes,
        lottieFile = null
    ),

    KT(
        teamName = "KT 위즈",
        isBlueStadium = true,
        backgroundColor = myPageBackgroundBlue,
        teamTagBackgroundColor = KBongTeamGray10,
        lottieFile = null
    ),

    HANHWA(
        teamName = "한화 이글즈",
        isBlueStadium = false,
        backgroundColor = myPageBackgroundRed,
        teamTagBackgroundColor = KBongTeamEagles,
        lottieFile = null
    ),


    KIA(
        teamName = "KIA 타이거즈",
        isBlueStadium = false,
        backgroundColor = myPageBackgroundRed,
        teamTagBackgroundColor = KBongTeamTigers,
        lottieFile = null
    ),

    NONE(
        teamName = "모두 응원해요",
        isBlueStadium = true,
        backgroundColor = myPageBackgroundBlue,
        teamTagBackgroundColor = KBongPrimary,
        lottieFile = null
    );

    companion object {
        fun fromTypeData(teamName: String): MyTeamType {
            return MyTeamType.entries.find { it.teamName == teamName } ?: NONE
        }
    }
}