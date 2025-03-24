package com.project.presentation.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

// LocalDate -> String 변환
fun LocalDate.localDateToString(): String {
    return this.format(formatter)
}

// String -> LocalDate 변환
fun String.stringToLocalDate(): LocalDate {
    return if (this.isEmpty())
        LocalDate.now()
    else
        LocalDate.parse(this, formatter)
}

// ex) LocalDate -> 3월 16일 일요일
fun LocalDate.formatLocalDate(): String {
    val koreanLocale = Locale("ko", "KR")

    val formatter = DateTimeFormatter.ofPattern("M월 d일 EEEE", koreanLocale)

    return this.format(formatter)
}

fun String.stringToFormatLocalData(): String =
    this.stringToLocalDate().formatLocalDate()
