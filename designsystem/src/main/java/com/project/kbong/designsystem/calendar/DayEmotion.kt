package com.project.kbong.designsystem.calendar

import androidx.annotation.DrawableRes
import com.project.kbong.designsystem.R

enum class DayEmotion(
    @DrawableRes val painter: Int,
    val title: String
) {
    HAPPY(painter = R.drawable.win, title = "HAPPY"),
    SAD(painter = R.drawable.lose, title = "SAD"),
    ANGRY(painter = R.drawable.lightning, title = "ANGRY"),
    NORMAL(painter = R.drawable.cloud, title = "NORMAL");

    companion object {
        fun fromDescription(title: String?): Int? {
            return entries.find { it.title == title }?.painter
        }
    }
}