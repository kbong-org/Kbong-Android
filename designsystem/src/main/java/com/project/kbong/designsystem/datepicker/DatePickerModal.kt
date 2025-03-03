package com.project.kbong.designsystem.datepicker

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.project.kbong.designsystem.R
import com.project.kbong.designsystem.theme.KBongTypography
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate?) -> Unit,
    onDismiss: () -> Unit
) {
    val initialSelectedDateMillis = selectedDate.atStartOfDay(ZoneId.of("UTC"))
        .toInstant().toEpochMilli()

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialSelectedDateMillis
    )

    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        colors = DatePickerDefaults.colors(
            containerColor = Color.White
        ),
        confirmButton = {
            TextButton(onClick = {
                val selectedMillis = datePickerState.selectedDateMillis
                val localDate = if (selectedMillis != null) {
                    Instant.ofEpochMilli(selectedMillis)
                        .atZone(ZoneId.of("UTC"))
                        .toLocalDate()
                } else {
                    null
                }
                onDateSelected(localDate)
                onDismiss()
            }) {
                Text(
                    text = stringResource(R.string.ok),
                    style = KBongTypography.Body1Normal
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = KBongTypography.Body1Normal
                )
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}