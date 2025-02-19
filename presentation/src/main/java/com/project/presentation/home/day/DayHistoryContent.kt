package com.project.presentation.home.day

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R
import com.project.presentation.utils.formatLocalDate
import java.time.LocalDate

@Composable
fun DayHistoryContent(
    selectedDate: LocalDate,
    itemCount: Int,
    onClickAddHistory: () -> Unit
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 19.dp),
                selectedDate = selectedDate,
                isAddIcon = itemCount < 3,
                onClickAddHistory = onClickAddHistory
            )

        }
    }
}

@Composable
fun DayHistoryHeader(
    modifier: Modifier,
    isAddIcon: Boolean,
    selectedDate: LocalDate,
    onClickAddHistory: () -> Unit
) {

    Row(
        modifier = modifier,
    ) {
        Text(
            text = selectedDate.formatLocalDate(),
            style = KBongTypography.Body1Normal
        )

        if (isAddIcon) {
            Spacer(modifier = Modifier.weight(1F))
            Image(
                modifier = Modifier.clickable { onClickAddHistory() },
                painter = painterResource(R.drawable.add_icon),
                contentDescription = "add_icon"
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewDayHistoryHeader() {
    DayHistoryContent(
        selectedDate = LocalDate.of(2025, 2, 17),
        itemCount = 1,
        onClickAddHistory = {}
    )
}