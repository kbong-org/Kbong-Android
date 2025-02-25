package com.project.kbong.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.kbong.designsystem.theme.*

@Composable
fun KBongButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = KBongPrimary,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) color else KBongGrayscaleGray3,
            contentColor = Color.White
        ),
        enabled = enabled
    ) {
        Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun KbongDiaryButton(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(110.dp)
            .height(40.dp)
            .background(backgroundColor, RoundedCornerShape(10.dp))
            .padding(horizontal = 16.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = KBongTypography.Label1Normal,
            color = textColor
        )
    }
}

@Composable
fun KBongOutlinedButton(
    text: String,
    backgroundColor: Color,
    borderColor: Color,
    textColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .width(172.dp)
            .height(56.dp)
            .border(1.dp, borderColor, RoundedCornerShape(14.dp))
            .background(backgroundColor, RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .padding(horizontal = 17.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = KBongTypography.Body2Normal,
            color = textColor
        )
    }
}

@Preview(showBackground = true, widthDp = 1000, heightDp = 900)
@Composable
fun KBongButtonPreview() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp) // 두 컬럼 간격
    ) {
        // [기록하러 가기] 버튼 컬럼
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "기록하러 가기", fontWeight = FontWeight.Bold, fontSize = 16.sp)

            val diaryButtons = listOf(
                KBongGrayscaleGray0 to KBongPrimary,
                KBongTeamHeroes10 to KBongTeamHeroes,
                KBongTeamBears10 to KBongTeamBears,
                KBongTeamGiants10 to KBongTeamGiants,
                KBongTeamEagles10 to KBongTeamEagles,
                KBongTeamTwins10 to KBongTeamTwins
            )

            diaryButtons.forEach { (bgColor, textColor) ->
                KbongDiaryButton(text = "기록하러 가기", backgroundColor = bgColor, textColor = textColor, onClick = {})
            }
        }

        // [다음] 버튼 컬럼
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "다음", fontWeight = FontWeight.Bold, fontSize = 16.sp)

            KBongButton(text = "다음", onClick = {})
            KBongButton(text = "다음", enabled = false, onClick = {})

            val teamButtons = listOf(
                KBongPrimary,
                KBongTeamHeroes,
                KBongTeamBears,
                KBongTeamGiants,
                KBongTeamLions,
                KBongTeamEagles,
                KBongTeamTigers,
                KBongTeamTwins,
                KBongTeamSsg,
                KBongTeamNc,
                KBongTeamKTSub,
                KBongGrayscaleGray10
            )

            teamButtons.forEach { color ->
                KBongButton(text = "다음", color = color, onClick = {})
            }
        }

        // 구단 선택 버튼 컬럼
        Column(
            modifier = Modifier.weight(2f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "구단 선택", fontWeight = FontWeight.Bold, fontSize = 16.sp)

            val teamButtons = listOf(
                listOf("모두 응원해요" to (KBongPrimary10 to KBongPrimary)),
                listOf(
                    "키움 히어로즈" to (KBongTeamHeroes10 to KBongTeamHeroes),
                    "두산 베어스" to (KBongTeamBears10 to KBongTeamBears)
                ),
                listOf(
                    "롯데 자이언츠" to (KBongTeamGiants10 to KBongTeamGiants),
                    "삼성 라이온즈" to (KBongTeamLions10 to KBongTeamLions)
                ),
                listOf(
                    "한화 이글즈" to (KBongTeamEagles10 to KBongTeamEagles),
                    "KIA 타이거즈" to (KBongTeamTigers10 to KBongTeamTigers)
                ),
                listOf(
                    "LG 트윈스" to (KBongTeamTwins10 to KBongTeamTwins),
                    "SSG 랜더스" to (KBongTeamSsg10 to KBongTeamSsg)
                ),
                listOf(
                    "NC 다이노스" to (KBongTeamNcSub10 to KBongTeamNc),
                    "KT 위즈" to (KBongTeamGray2 to KBongTeamGray10)
                )
            )

            teamButtons.forEachIndexed { index, rowButtons ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowButtons.forEach { (text, colors) ->
                        KBongOutlinedButton(
                            text = text,
                            backgroundColor = colors.first,
                            borderColor = colors.second,
                            textColor = colors.second,
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}

