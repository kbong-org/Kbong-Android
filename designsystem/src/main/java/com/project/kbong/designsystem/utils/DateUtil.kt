package com.project.kbong.designsystem.utils

import java.util.Calendar

object DateUtil {

    private val currentDate = Calendar.getInstance()

    fun getYearInt(): Int {
        return currentDate.get(Calendar.YEAR)
    }
}