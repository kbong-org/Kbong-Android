package com.project.kbong.designsystem.tag

import androidx.compose.ui.graphics.Color

data class TagTypeData(
    val type: String,
    val tagName: String,
    val textColor: Color,
    val backgroundColor: Color
)

object TagTypeMapper {
    fun fromType(
        type: String,
        textColor: Color,
        backgroundColor: Color
    ): TagTypeData {
        return when (type) {
            "CHOICE" -> TagTypeData(
                type = "CHOICE",
                tagName = "선택형",
                textColor = textColor,
                backgroundColor = backgroundColor
            )

            "FREE" -> TagTypeData(
                type = "FREE",
                tagName = "자유형",
                textColor = textColor,
                backgroundColor = backgroundColor
            )

            "SHORT" -> TagTypeData(
                type = "SHORT",
                tagName = "주관형",
                textColor = textColor,
                backgroundColor = backgroundColor
            )

            else -> TagTypeData(
                type = "CHOICE",
                tagName = "선택형",
                textColor = textColor,
                backgroundColor = backgroundColor
            )
        }
    }
}