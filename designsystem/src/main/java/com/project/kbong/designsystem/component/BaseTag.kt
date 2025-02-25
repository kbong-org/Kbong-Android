package com.project.kbong.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongGrayscaleGray0
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongPrimary10
import com.project.kbong.designsystem.theme.KBongTeamBears
import com.project.kbong.designsystem.theme.KBongTeamBears10
import com.project.kbong.designsystem.theme.KBongTeamEagles
import com.project.kbong.designsystem.theme.KBongTeamEagles10
import com.project.kbong.designsystem.theme.KBongTeamGiants
import com.project.kbong.designsystem.theme.KBongTeamGiants10
import com.project.kbong.designsystem.theme.KBongTeamHeroes
import com.project.kbong.designsystem.theme.KBongTeamHeroes10
import com.project.kbong.designsystem.theme.KBongTeamKTSub
import com.project.kbong.designsystem.theme.KBongTeamLions
import com.project.kbong.designsystem.theme.KBongTeamLions10
import com.project.kbong.designsystem.theme.KBongTeamNc
import com.project.kbong.designsystem.theme.KBongTeamNcSub10
import com.project.kbong.designsystem.theme.KBongTeamSsg
import com.project.kbong.designsystem.theme.KBongTeamSsg10
import com.project.kbong.designsystem.theme.KBongTeamTigers
import com.project.kbong.designsystem.theme.KBongTeamTigers10
import com.project.kbong.designsystem.theme.KBongTeamTwins
import com.project.kbong.designsystem.theme.KBongTeamTwins10
import com.project.kbong.designsystem.theme.KBongTypography

@Composable
fun KBongTag(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .wrapContentWidth()
            .height(22.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = KBongTypography.Caption2.copy(color = textColor)
        )
    }
}

@Composable
fun KBongTeamTag(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(50.dp)
            .height(28.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .padding(horizontal = 14.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = KBongTypography.Caption1.copy(color = textColor)
        )
    }
}

@Preview
@Composable
fun KbongTagPreview() {
    val tags = listOf(
        Triple("태그", KBongPrimary10, KBongPrimary),
        Triple("태그", KBongTeamHeroes10, KBongTeamHeroes),
        Triple("태그", KBongTeamBears10, KBongTeamBears),
        Triple("태그", KBongTeamGiants10, KBongTeamGiants),
        Triple("태그", KBongTeamLions10, KBongTeamLions),
        Triple("태그", KBongTeamEagles10, KBongTeamEagles),
        Triple("태그", KBongTeamTigers10, KBongTeamTigers),
        Triple("태그", KBongTeamTwins10, KBongTeamTwins),
        Triple("태그", KBongTeamSsg10, KBongTeamSsg),
        Triple("태그", KBongTeamNcSub10, KBongTeamNc)
    )

    val teams = listOf(
        Triple("구단", KBongTeamBears, KBongGrayscaleGray0),
        Triple("구단", KBongTeamGiants, KBongGrayscaleGray0),
        Triple("구단", KBongTeamLions, KBongGrayscaleGray0),
        Triple("구단", KBongTeamEagles, KBongGrayscaleGray0),
        Triple("구단", KBongTeamHeroes, KBongGrayscaleGray0),
        Triple("구단", KBongTeamTigers, KBongGrayscaleGray0),
        Triple("구단", KBongTeamTwins, KBongGrayscaleGray0),
        Triple("구단", KBongTeamSsg, KBongGrayscaleGray0),
        Triple("구단", KBongTeamNc, KBongGrayscaleGray0),
        Triple("구단", KBongTeamKTSub, KBongGrayscaleGray0),
    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 기본 태그 하나
        KBongTag(
            text = "태그",
            backgroundColor = KBongPrimary10,
            textColor = KBongPrimary
        )

        // 2줄 5개씩 배치
        tags.chunked(5).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { (text, bgColor, borderColor) ->
                    KBongTag(
                        text = text,
                        backgroundColor = bgColor,
                        textColor = borderColor
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 5개씩 2줄 배치
            teams.chunked(5).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowItems.forEach { (text, bgColor, textColor) ->
                        KBongTeamTag(
                            text = text,
                            backgroundColor = bgColor,
                            textColor = textColor
                        )
                    }
                }
            }

            // 마지막 줄: 기본 컬러 버튼 1개
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                KBongTeamTag(
                    text = "구단",
                    backgroundColor = KBongPrimary,
                    textColor = KBongGrayscaleGray0
                )
            }
        }
    }
}