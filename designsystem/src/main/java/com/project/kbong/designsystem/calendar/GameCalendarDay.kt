package com.project.kbong.designsystem.calendar


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.domain.model.calendar.GameDayContent
import com.project.kbong.designsystem.tag.GameResultType
import com.project.kbong.designsystem.tag.KBongGameResultTag
import com.project.kbong.designsystem.theme.KBongGrayscaleGray0
import com.project.kbong.designsystem.theme.KBongGrayscaleGray1
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongTeamGray10
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.kbong.designsystem.utils.DateUtil
import java.time.LocalDate

@Composable
fun GameCalendarDay(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    conversionLocalDate: LocalDate,
    isMyTeam: Boolean = false,
    gameDayContent: GameDayContent,
    teamColor: Color,
    onSelectedDate: () -> Unit
) {
    val gameResult: GameResultType? = GameResultType.fromTypeData(gameDayContent.result)
    val isSelected = gameDayContent.day == selectedDate.dayOfMonth.toString()
    val currentDateColor = when {
        isSelected -> teamColor
        DateUtil.today() == conversionLocalDate -> KBongGrayscaleGray1
        else -> KBongGrayscaleGray0
    }

    Column(
        modifier = modifier
            .defaultMinSize(minHeight = 48.dp)
            .clickable { onSelectedDate() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(200.dp))
                .background(currentDateColor)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                text = gameDayContent.day,
                style = KBongTypography.Label1Normal,
                color = if (isSelected) KBongGrayscaleGray0 else KBongTeamGray10
            )
        }

        if (
            !isMyTeam ||
            gameDayContent.hasGame &&
            DateUtil.today() <= conversionLocalDate
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 14.dp, bottom = 10.dp)
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(KBongGrayscaleGray2)
                    .align(Alignment.CenterHorizontally)
            ) {}
        } else {
            gameResult?.let { data ->
                KBongGameResultTag(
                    gameResultType = data
                )
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun PreviewCalendarDay() {
    GameCalendarDay(
        selectedDate = LocalDate.now(),
        conversionLocalDate = LocalDate.of(2024, 6, 21),
        isMyTeam = false,
        gameDayContent = GameDayContent(
            day = "1",
            hasGame = true,
            result = "WIN"
        ),
        teamColor = KBongPrimary,
        onSelectedDate = {

        },
    )
}