package com.project.presentation.log.write.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.domain.model.day.DailyGameLog
import com.project.kbong.designsystem.theme.KBongGrayscaleGray1
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R
import com.project.presentation.utils.formatLocalDate
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Composable
fun HeaderGameInfo(
    game: DailyGameLog,
    selectedDate: LocalDate,
    selectedEmotion: Int,
    onEmotionSelected: (Int) -> Unit,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Image(
                painter = painterResource(id = selectedEmotion),
                contentDescription = null,
                modifier = Modifier
                    .size(42.dp)
                    .clickable { isMenuExpanded = true }
            )

            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false },
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    listOf(
                        com.project.kbong.designsystem.R.drawable.win,
                        com.project.kbong.designsystem.R.drawable.cloud,
                        com.project.kbong.designsystem.R.drawable.lose,
                        com.project.kbong.designsystem.R.drawable.lightning
                    ).forEach { resId ->
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = null,
                            modifier = Modifier
                                .size(42.dp)
                                .clickable {
                                    onEmotionSelected(resId)
                                    isMenuExpanded = false
                                }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .wrapContentSize(align = Alignment.Center)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "시간"
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = selectedDate.formatLocalDate(),
                    style = KBongTypography.Label2Medium,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .wrapContentSize(align = Alignment.Center)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = "장소",
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = game.stadiumDisplayName,
                    style = KBongTypography.Label2Medium,
                    color = Color.Gray
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
    Divider(
        thickness = 6.dp,
        color = KBongGrayscaleGray1,
    )
    Spacer(modifier = Modifier.height(8.dp))
}