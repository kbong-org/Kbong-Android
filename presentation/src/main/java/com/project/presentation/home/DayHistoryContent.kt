package com.project.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.utils.formatLocalDate
import java.time.LocalDate

@Composable
fun DayHistoryContent(
    selectedDate: LocalDate
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            DayHistoryHeader(
                modifier = Modifier.padding(vertical = 19.dp),
                selectedDate = selectedDate
            )
        }
    }
}

@Composable
fun DayHistoryHeader(
    modifier: Modifier,
    selectedDate: LocalDate
) {

    Text(
        modifier = modifier,
        text = selectedDate.formatLocalDate(),
        style = KBongTypography.Body1Normal
    )

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewDayHistoryHeader(){
    DayHistoryHeader(
        modifier = Modifier.padding(vertical = 19.dp),
        selectedDate = LocalDate.of(2025,2,17)
    )
}