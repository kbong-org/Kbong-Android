package com.project.kbong.designsystem.utils

import androidx.compose.ui.graphics.Color
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongPrimary10
import com.project.kbong.designsystem.theme.KBongTeamBears
import com.project.kbong.designsystem.theme.KBongTeamBears10
import com.project.kbong.designsystem.theme.KBongTeamEagles
import com.project.kbong.designsystem.theme.KBongTeamEagles10
import com.project.kbong.designsystem.theme.KBongTeamGiants
import com.project.kbong.designsystem.theme.KBongTeamGiants10
import com.project.kbong.designsystem.theme.KBongTeamGray10
import com.project.kbong.designsystem.theme.KBongTeamGray2
import com.project.kbong.designsystem.theme.KBongTeamHeroes
import com.project.kbong.designsystem.theme.KBongTeamHeroes10
import com.project.kbong.designsystem.theme.KBongTeamLions
import com.project.kbong.designsystem.theme.KBongTeamLions10
import com.project.kbong.designsystem.theme.KBongTeamNc
import com.project.kbong.designsystem.theme.KBongTeamNcSub10
import com.project.kbong.designsystem.theme.KBongTeamSsg
import com.project.kbong.designsystem.theme.KBongTeamSsg10
import com.project.kbong.designsystem.theme.KBongTeamTigers
import com.project.kbong.designsystem.theme.KBongTeamTigers10
import com.project.kbong.designsystem.theme.KBongTeamTwins
import com.project.kbong.designsystem.theme.KBongTeamTwins10

object TeamColorMapper {
    private val teamCodeMap = mapOf(
        "LG" to "LG",
        "두산" to "DOOSAN",
        "한화" to "HANHWA",
        "KIA" to "KIA",
        "KT" to "KT",
        "NC" to "NC",
        "삼성" to "SAMSUNG",
        "SSG" to "SSG",
        "롯데" to "LOTTE",
        "키움" to "KIWOOM"
    )

//    fun getTextColorFromDisplayName(displayName: String): Color {
//        val teamCode = teamCodeMap.entries.find { displayName.contains(it.key, ignoreCase = true) }?.value
//        return getTextColor(teamCode ?: "")
//    }
//
//    fun getBackgroundColorFromDisplayName(displayName: String): Color {
//        val teamCode = teamCodeMap.entries.find { displayName.contains(it.key, ignoreCase = true) }?.value
//        return getBackgroundColor(teamCode ?: "")
//    }

    fun getTextColor(teamCode: String): Color = when (teamCode.uppercase()) {
        "LG" -> KBongTeamTwins
        "두산" -> KBongTeamBears
        "한화" -> KBongTeamEagles
        "KIA" -> KBongTeamTigers
        "KT" -> KBongTeamGray10
        "NC" -> KBongTeamNc
        "삼성" -> KBongTeamLions
        "SSG" -> KBongTeamSsg
        "롯데" -> KBongTeamGiants
        "키움" -> KBongTeamHeroes
        else -> KBongPrimary
    }

    fun getBackgroundColor(teamCode: String): Color = when (teamCode.uppercase()) {
        "LG" -> KBongTeamTwins10
        "두산" -> KBongTeamBears10
        "한화" -> KBongTeamEagles10
        "KIA" -> KBongTeamTigers10
        "KT" -> KBongTeamGray2
        "NC" -> KBongTeamNcSub10
        "삼성" -> KBongTeamLions10
        "SSG" -> KBongTeamSsg10
        "롯데" -> KBongTeamGiants10
        "키움" -> KBongTeamHeroes10
        else -> KBongPrimary10
    }
}