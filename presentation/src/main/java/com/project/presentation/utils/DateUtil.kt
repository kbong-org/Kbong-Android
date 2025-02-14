package com.project.presentation.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
