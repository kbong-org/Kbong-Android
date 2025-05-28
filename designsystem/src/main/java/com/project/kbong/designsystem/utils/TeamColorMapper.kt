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
        "DOOSAN" to "DOOSAN",
        "HANHWA" to "HANHWA",
        "KIA" to "KIA",
        "KT" to "KT",
        "NC" to "NC",
        "SAMSUNG" to "SAMSUNG",
        "SSG" to "SSG",
        "LOTTE" to "LOTTE",
        "KIWOOM" to "KIWOOM"
    )

    fun getTextColorFromDisplayName(displayName: String): Color {
        val teamCode = teamCodeMap.entries.find { displayName.contains(it.key, ignoreCase = true) }?.value
        return getTextColor(teamCode ?: "")
    }

    fun getBackgroundColorFromDisplayName(displayName: String): Color {
        val teamCode = teamCodeMap.entries.find { displayName.contains(it.key, ignoreCase = true) }?.value
        return getBackgroundColor(teamCode ?: "")
    }

    fun getTextColor(teamCode: String): Color = when (teamCode.uppercase()) {
        "LG" -> KBongTeamTwins
        "DOOSAN" -> KBongTeamBears
        "HANHWA" -> KBongTeamEagles
        "KIA" -> KBongTeamTigers
        "KT" -> KBongTeamGray10
        "NC" -> KBongTeamNc
        "SAMSUNG" -> KBongTeamLions
        "SSG" -> KBongTeamSsg
        "LOTTE" -> KBongTeamGiants
        "KIWOOM" -> KBongTeamHeroes
        else -> KBongPrimary
    }

    fun getBackgroundColor(teamCode: String): Color = when (teamCode.uppercase()) {
        "LG" -> KBongTeamTwins10
        "DOOSAN" -> KBongTeamBears10
        "HANHWA" -> KBongTeamEagles10
        "KIA" -> KBongTeamTigers10
        "KT" -> KBongTeamGray2
        "NC" -> KBongTeamNcSub10
        "SAMSUNG" -> KBongTeamLions10
        "SSG" -> KBongTeamSsg10
        "LOTTE" -> KBongTeamGiants10
        "KIWOOM" -> KBongTeamHeroes10
        else -> KBongPrimary10
    }
}