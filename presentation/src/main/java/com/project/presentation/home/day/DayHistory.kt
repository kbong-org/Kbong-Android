package com.project.presentation.home.day

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.project.kbong.designsystem.tag.KBongTag
import com.project.kbong.designsystem.tag.TagType
import com.project.kbong.designsystem.theme.KBongGrayscaleGray0
import com.project.kbong.designsystem.theme.KBongGrayscaleGray7
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongImageCount
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R

@Composable
fun DayHistory(
    awayTeam: String,
    homeTeam: String,
    stadium: String,
    type: String,
    imageList: List<String>,
) {
    val fromTypeData = TagType.fromTypeData(type)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = awayTeam,
                    style = KBongTypography.Heading2,
                    color = KBongGrayscaleGray8
                )
                Text(
                    text = stringResource(R.string.vs),
                    style = KBongTypography.Heading2,
                    color = KBongPrimary
                )
                Text(
                    text = homeTeam,
                    style = KBongTypography.Heading2,
                    color = KBongGrayscaleGray8
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.location),
                    contentDescription = "location"
                )
                Text(
                    text = stadium,
                    style = KBongTypography.Caption1.copy(fontWeight = FontWeight.Medium),
                    color = KBongGrayscaleGray7
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            KBongTag(
                tagName = fromTypeData.tagName,
                backgroundColor = fromTypeData.backgroundColor,
                textColor = fromTypeData.textColor
            )
        }

        Spacer(modifier = Modifier.weight(1F))

        Box {
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = painterResource(com.project.kbong.designsystem.R.drawable.default_thumbnail),
                model = imageList.first(),
                contentDescription = "historyImage"
            )
            if (imageList.size > 1) {
                Box(
                    modifier = Modifier
                        .padding(top = 6.dp, end = 6.dp)
                        .clip(CircleShape)
                        .background(KBongImageCount)
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                        text = "${imageList.size}",
                        style = KBongTypography.Caption2,
                        color = KBongGrayscaleGray0
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewDayHistory() {
    DayHistory(
        awayTeam = "KT",
        homeTeam = "삼성",
        stadium = "수원 KT위즈파크",
        type = "CHOICE",
        imageList = listOf(
            "https://mblogthumb-phinf.pstatic.net/MjAyMDA5MTJfNTkg/MDAxNTk5OTA0OTYzNDk1.Ct3_Y6k_Cyx0Lh8w0w3O1gxG6Q-ApWy1y0rj91p7pwMg.QS9CAOcH6cX0zTaHa449f4hcOj7MruepMCwI1xALX44g.JPEG.kn010123/IMG_1521.JPG?type=w800",
            "https://mblogthumb-phinf.pstatic.net/MjAyMDA5MTJfNTkg/MDAxNTk5OTA0OTYzNDk1.Ct3_Y6k_Cyx0Lh8w0w3O1gxG6Q-ApWy1y0rj91p7pwMg.QS9CAOcH6cX0zTaHa449f4hcOj7MruepMCwI1xALX44g.JPEG.kn010123/IMG_1521.JPG?type=w800",
        ),
    )
}