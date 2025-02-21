package com.project.kbong.designsystem.tag

import androidx.compose.ui.graphics.Color
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongPrimary10

enum class TagType(
    val type: String,
    val textColor: Color,
    val tagName: String,
    val backgroundColor: Color
) {
    CHOICE(
        type = "CHOICE",
        tagName = "선택형",
        textColor = KBongPrimary,
        backgroundColor = KBongPrimary10
    );

    companion object {
        fun fromTypeData(type: String): TagType {
            return TagType.entries.find { it.type == type } ?: CHOICE
        }
    }

}