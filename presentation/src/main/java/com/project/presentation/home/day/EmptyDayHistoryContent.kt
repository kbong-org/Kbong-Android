package com.project.presentation.home.day

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongGrayscaleGray5
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongPrimary10
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R

@Composable
fun EmptyDayHistoryContent(
    modifier: Modifier = Modifier,
    isGoLogButton: Boolean = true,
    onClickGoLog: () -> Unit = {},
    teamColor: Color,
    teamColorBg: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.note),
            contentDescription = "note"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.empty_day_log),
            style = KBongTypography.Label2Medium,
            color = KBongGrayscaleGray5
        )
        if (isGoLogButton) {
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { onClickGoLog() },
                colors = ButtonColors(
                    containerColor = teamColorBg,
                    contentColor = ButtonDefaults.buttonColors().contentColor,
                    disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor,
                    disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
                ),
                contentPadding = PaddingValues(vertical = 10.dp, horizontal = 16.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = stringResource(R.string.go_log),
                    style = KBongTypography.Label1Reading,
                    color = teamColor
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewEmptyDayHistoryContent() {
    EmptyDayHistoryContent(
        onClickGoLog = {},
        teamColor = KBongPrimary,
        teamColorBg = KBongPrimary10
    )
}