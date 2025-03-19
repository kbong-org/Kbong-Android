package com.project.kbong.designsystem.tag

import androidx.compose.ui.graphics.Color
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongGrayscaleGray7
import com.project.kbong.designsystem.theme.KBongStatusDestructive
import com.project.kbong.designsystem.theme.KBongStatusDestructive10

enum class GameStatusType(
    val type: String,
    val textColor: Color,
    val tagName: String,
    val backgroundColor: Color
) {
    FINISHED(
        type = "FINISHED",
        tagName = "종료",
        textColor = KBongGrayscaleGray7,
        backgroundColor = KBongGrayscaleGray2
    ),

    CANCELLED(
        type = "CANCELLED",
        tagName = "취소",
        textColor = KBongStatusDestructive,
        backgroundColor = KBongStatusDestructive10
    ),

    READIED(
        type = "READIED",
        tagName = "예정",
        textColor = KBongGrayscaleGray7,
        backgroundColor = KBongGrayscaleGray2
    );


    companion object {
        fun fromTypeData(type: String): GameStatusType {
            return GameStatusType.entries.find { it.type == type } ?: READIED
        }
    }


}