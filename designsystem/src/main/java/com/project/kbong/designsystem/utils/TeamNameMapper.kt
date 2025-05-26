package com.project.kbong.designsystem.utils

object TeamNameMapper {
    private val teamNameMap = mapOf(
        "LG" to "LG 트윈스",
        "DOOSAN" to "두산 베어스",
        "HANHWA" to "한화 이글스",
        "KIA" to "KIA 타이거즈",
        "KT" to "KT 위즈",
        "NC" to "NC 다이노스",
        "SAMSUNG" to "삼성 라이온즈",
        "SSG" to "SSG 랜더스",
        "LOTTE" to "롯데 자이언츠",
        "KIWOOM" to "키움 히어로즈"
    )

    fun getDisplayName(code: String): String {
        return teamNameMap[code] ?: code
    }
}