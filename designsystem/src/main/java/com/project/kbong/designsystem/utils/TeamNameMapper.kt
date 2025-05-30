package com.project.kbong.designsystem.utils

object TeamNameMapper {
    private val teamNameMap = mapOf(
        "LG" to "LG 트윈스",
        "두산" to "두산 베어스",
        "한화" to "한화 이글스",
        "KIA" to "KIA 타이거즈",
        "KT" to "KT 위즈",
        "NC" to "NC 다이노스",
        "삼성" to "삼성 라이온즈",
        "SSG" to "SSG 랜더스",
        "롯데" to "롯데 자이언츠",
        "키움" to "키움 히어로즈",

        "LG 트윈스" to "LG",
        "DOOSAN" to "두산",
        "HANHWA" to "한화",
        "KIA 타이거즈" to "KIA",
        "KT 위즈" to "KT",
        "NC 다이노스" to "NC",
        "SAMSUNG" to "삼성",
        "SSG 랜더스" to "SSG",
        "LOTTE" to "롯데",
        "KIWOOM" to "키움"
    )

    fun getDisplayName(code: String): String {
        return teamNameMap[code] ?: code
    }
}