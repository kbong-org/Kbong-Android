package com.project.kbong.designsystem.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.project.kbong.designsystem.R

object FontSizeTokens {
    val title1 = 24.sp
    val Heading1 = 20.sp
    val Heading2 = 17.sp
    val Body1 = 16.sp
    val Body2 = 15.sp
    val Label1 = 14.sp
    val Label2 = 13.sp
    val Caption1 = 12.sp
    val Caption2 = 11.sp
    val Caption3 = 10.sp
}

object KBongTypography {
    private val baseTextStyle = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.pretendard_bold, FontWeight.Bold),
            Font(R.font.pretendard_medium, FontWeight.SemiBold),
            Font(R.font.pretendard_regular, FontWeight.Normal),
            Font(R.font.pretendard_medium, FontWeight.Medium),
            Font(R.font.pretendard_light, FontWeight.Light),
            Font(R.font.pretendard_thin, FontWeight.Thin),
        ),
    )

    val Title = baseTextStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = FontSizeTokens.title1,
        letterSpacing = (-0.023).em,
        lineHeight = 32.sp
    )

    val Heading1 = baseTextStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = FontSizeTokens.Heading1,
        letterSpacing = (-0.012).em,
        lineHeight = 28.sp
    )

    val Heading2 = baseTextStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = FontSizeTokens.Heading2,
        letterSpacing = 0.em,
        lineHeight = 24.sp
    )

    val Body1Normal = baseTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeTokens.Body1,
        letterSpacing = 0.057.em,
        lineHeight = 24.sp
    )

    val Body1NormalSemiBold = baseTextStyle.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = FontSizeTokens.Body1,
        letterSpacing = 0.057.em,
        lineHeight = 24.sp
    )

    val Body1Reading = baseTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeTokens.Body1,
        letterSpacing = 0.057.em,
        lineHeight = 26.sp
    )

    val Body2Normal = baseTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeTokens.Body2,
        letterSpacing = 0.096.em,
        lineHeight = 22.sp
    )

    val Body2Reading = baseTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeTokens.Body2,
        letterSpacing = 0.096.em,
        lineHeight = 24.sp
    )

    val Label1Normal = baseTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeTokens.Label1,
        letterSpacing = 0.0145.em,
        lineHeight = 20.sp
    )

    val Label1Regular = baseTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeTokens.Label1,
        letterSpacing = 0.0145.em,
        lineHeight = 20.sp
    )

    val Label1Reading = baseTextStyle.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = FontSizeTokens.Label1,
        letterSpacing = 0.0145.em,
        lineHeight = 22.sp
    )

    val Label2 = baseTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeTokens.Label2,
        letterSpacing = 0.0194.em,
        lineHeight = 18.sp
    )

    val Label2Medium = baseTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeTokens.Label2,
        letterSpacing = 0.0194.em,
        lineHeight = 18.sp
    )

    val Label2Regular = baseTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeTokens.Label2,
        letterSpacing = 0.0194.em,
        lineHeight = 18.sp
    )

    val Caption1 = baseTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeTokens.Caption1,
        letterSpacing = 0.0252.em,
        lineHeight = 16.sp
    )

    val Caption1Medium = baseTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeTokens.Caption1,
        letterSpacing = 0.0252.em,
        lineHeight = 16.sp
    )

    val Caption2 = baseTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeTokens.Caption2,
        letterSpacing = 0.0311.em,
        lineHeight = 14.sp
    )

    val Caption3Bold = baseTextStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = FontSizeTokens.Caption3,
    )

    val CalendarHeader = baseTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeTokens.Label1,
        letterSpacing = (-0.02).em,
        lineHeight = 14.sp
    )
}