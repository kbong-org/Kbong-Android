package com.project.kbong.designsystem.tag

import androidx.compose.ui.graphics.Color
import com.project.kbong.designsystem.theme.KBongAccentButtonBlue
import com.project.kbong.designsystem.theme.KBongAccentButtonBlue10
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongStatusDestructive
import com.project.kbong.designsystem.theme.KBongStatusDestructive10

enum class GameResultType(
    val type: String,
    val textColor: Color,
    val tagName: String,
    val backgroundColor: Color
) {
    WIN(
        type = "WIN",
        tagName = "승",
        textColor = KBongAccentButtonBlue,
        backgroundColor = KBongAccentButtonBlue10
    ),

    LOSE(
        type = "LOSE",
        tagName = "패",
        textColor = KBongStatusDestructive,
        backgroundColor = KBongStatusDestructive10
    ),

    DRAW(
        type = "DRAW",
        tagName = "무",
        textColor = KBongGrayscaleGray8,
        backgroundColor = KBongGrayscaleGray2
    );

    companion object {
        fun fromTypeData(type: String): GameResultType {
            return GameResultType.entries.find { it.type == type } ?: WIN
        }
    }

}