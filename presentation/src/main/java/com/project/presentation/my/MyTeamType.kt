package com.project.presentation.my

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongPrimary10
import com.project.kbong.designsystem.theme.KBongTeamBears
import com.project.kbong.designsystem.theme.KBongTeamBearsSub10
import com.project.kbong.designsystem.theme.KBongTeamEagles
import com.project.kbong.designsystem.theme.KBongTeamEaglesSub10
import com.project.kbong.designsystem.theme.KBongTeamGiants
import com.project.kbong.designsystem.theme.KBongTeamGiantsSub10
import com.project.kbong.designsystem.theme.KBongTeamGray10
import com.project.kbong.designsystem.theme.KBongTeamGray2
import com.project.kbong.designsystem.theme.KBongTeamHeroes
import com.project.kbong.designsystem.theme.KBongTeamHeroesSub10
import com.project.kbong.designsystem.theme.KBongTeamLions
import com.project.kbong.designsystem.theme.KBongTeamLionsSub10
import com.project.kbong.designsystem.theme.KBongTeamNc
import com.project.kbong.designsystem.theme.KBongTeamNcSub10
import com.project.kbong.designsystem.theme.KBongTeamSsg
import com.project.kbong.designsystem.theme.KBongTeamSsgSub10
import com.project.kbong.designsystem.theme.KBongTeamTigers
import com.project.kbong.designsystem.theme.KBongTeamTigersSub10
import com.project.kbong.designsystem.theme.KBongTeamTwins
import com.project.kbong.designsystem.theme.KBongTeamTwins10
import com.project.kbong.designsystem.theme.myPageBackgroundBlue
import com.project.kbong.designsystem.theme.myPageBackgroundRed

enum class MyTeamType(
    val code: String,
    val teamName: String,
    val backgroundColor: Color,
    val teamTagBackgroundColor: Color,
    val teamSub10Color: Color,
    val isBlueStadium: Boolean = true,
    val lottieFile: Drawable? = null,
    val infoImage: Int
) {
    DOOSAN(
        code = "DOOSAN",
        teamName = "두산 베어스",
        isBlueStadium = true,
        teamSub10Color = KBongTeamBearsSub10,
        backgroundColor = myPageBackgroundBlue,
        teamTagBackgroundColor = KBongTeamBears,
        lottieFile = null,
        infoImage = com.project.kbong.designsystem.R.drawable.blue_info_edit
    ),

    LOTTE(
        code = "LOTTE",
        teamName = "롯데 자이언츠",
        isBlueStadium = true,
        teamSub10Color = KBongTeamGiantsSub10,
        backgroundColor = myPageBackgroundBlue,
        teamTagBackgroundColor = KBongTeamGiants,
        lottieFile = null,
        infoImage = com.project.kbong.designsystem.R.drawable.blue_info_edit
    ),

    SAMSUNG(
        code = "SAMSUNG",
        teamName = "삼성 라이온즈",
        teamSub10Color = KBongTeamLionsSub10,
        isBlueStadium = true,
        backgroundColor = myPageBackgroundBlue,
        teamTagBackgroundColor = KBongTeamLions,
        lottieFile = null,
        infoImage = com.project.kbong.designsystem.R.drawable.blue_info_edit
    ),

    SSG(
        code = "SSG",
        teamName = "SSG 랜더스",
        isBlueStadium = false,
        teamSub10Color = KBongTeamSsgSub10,
        backgroundColor = myPageBackgroundRed,
        teamTagBackgroundColor = KBongTeamSsg,
        lottieFile = null,
        infoImage = com.project.kbong.designsystem.R.drawable.red_info_edit
    ),

    NC(
        code = "NC",
        teamName = "NC 다이노스",
        isBlueStadium = true,
        teamSub10Color = KBongTeamNcSub10,
        backgroundColor = myPageBackgroundBlue,
        teamTagBackgroundColor = KBongTeamNc,
        lottieFile = null,
        infoImage = com.project.kbong.designsystem.R.drawable.blue_info_edit
    ),

    LG(
        code = "LG",
        teamName = "LG 트윈스",
        isBlueStadium = false,
        teamSub10Color = KBongTeamTwins10,
        backgroundColor = myPageBackgroundRed,
        teamTagBackgroundColor = KBongTeamTwins,
        lottieFile = null,
        infoImage = com.project.kbong.designsystem.R.drawable.red_info_edit
    ),

    KIWOOM(
        code = "KIWOOM",
        teamName = "키움 히어로즈",
        isBlueStadium = false,
        teamSub10Color = KBongTeamHeroesSub10,
        backgroundColor = myPageBackgroundRed,
        teamTagBackgroundColor = KBongTeamHeroes,
        lottieFile = null,
        infoImage = com.project.kbong.designsystem.R.drawable.red_info_edit
    ),

    KT(
        code = "KT",
        teamName = "KT 위즈",
        isBlueStadium = true,
        teamSub10Color = KBongTeamGray2,
        backgroundColor = myPageBackgroundBlue,
        teamTagBackgroundColor = KBongTeamGray10,
        lottieFile = null,
        infoImage = com.project.kbong.designsystem.R.drawable.blue_info_edit
    ),

    HANHWA(
        code = "HANHWA",
        teamName = "한화 이글즈",
        isBlueStadium = false,
        teamSub10Color = KBongTeamEaglesSub10,
        backgroundColor = myPageBackgroundRed,
        teamTagBackgroundColor = KBongTeamEagles,
        lottieFile = null,
        infoImage = com.project.kbong.designsystem.R.drawable.red_info_edit
    ),

    KIA(
        code = "KIA",
        teamName = "KIA 타이거즈",
        isBlueStadium = false,
        teamSub10Color = KBongTeamTigersSub10,
        backgroundColor = myPageBackgroundRed,
        teamTagBackgroundColor = KBongTeamTigers,
        lottieFile = null,
        infoImage = com.project.kbong.designsystem.R.drawable.red_info_edit
    ),

    NONE(
        code = "NONE",
        teamName = "모두 응원해요",
        isBlueStadium = true,
        teamSub10Color = KBongPrimary10,
        backgroundColor = myPageBackgroundBlue,
        teamTagBackgroundColor = KBongPrimary,
        lottieFile = null,
        infoImage = com.project.kbong.designsystem.R.drawable.blue_info_edit
    );

    companion object {
        fun fromCode(code: String): MyTeamType {
            return entries.find { it.code == code } ?: NONE
        }
    }
}