package com.project.kbong.designsystem.calender

import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.R
import com.project.kbong.designsystem.theme.KBongGrayscaleGray0
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongTeamGray10

@Composable
fun CalendarDay(
    modifier: Modifier = Modifier,
    calendar: Calendar,
    isWin: Boolean,
    isSelected: Boolean,
    onSelectedDate: (Calendar) -> Unit
) {
    val painterImage = painterResource(
        if (isWin) {
            R.drawable.win
        } else {
            R.drawable.lose
        }
    )


    Column(
        modifier = modifier.clickable { onSelectedDate(calendar) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(200.dp))
                .background(if (isSelected) KBongPrimary else KBongGrayscaleGray0)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = calendar.get(Calendar.DAY_OF_MONTH).toString(),
                color = if (isSelected) KBongGrayscaleGray0 else KBongTeamGray10
            )
        }

        Image(
            painter = painterImage,
            contentDescription = "winOrLose"
        )
    }
}

@Preview
@Composable
private fun PreviewCalendarDay() {
    CalendarDay(
        calendar = Calendar.getInstance(),
        isWin = false,
        isSelected = false,
        onSelectedDate = {}
    )
}