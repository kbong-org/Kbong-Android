package com.project.presentation.home.day

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongGrayscaleGray5
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R

@Composable
fun BeforeDayHistoryContent(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 58.dp, bottom = 57.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.stadium),
            contentDescription = "empty_game"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.empty_day_log_before_6am),
            style = KBongTypography.Label2Medium,
            color = KBongGrayscaleGray5
        )
    }
}

@Preview
@Composable
private fun PreviewBeforeDayHistoryContent() {
    BeforeDayHistoryContent()
}