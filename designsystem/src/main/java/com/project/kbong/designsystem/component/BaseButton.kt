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
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
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

@Composable
fun KBongTeamButton(
    teamName: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val teamColor = when (teamName) {
        "키움 히어로즈" -> KBongTeamHeroes
        "두산 베어스" -> KBongTeamBears
        "롯데 자이언츠" -> KBongTeamGiants
        "삼성 라이온즈" -> KBongTeamLions
        "한화 이글즈" -> KBongTeamEagles
        "KIA 타이거즈" -> KBongTeamTigers
        "LG 트윈스" -> KBongTeamTwins
        "SSG 랜더스" -> KBongTeamSsg
        "NC 다이노스" -> KBongTeamNc
        "KT 위즈" -> KBongTeamKTSub
        else -> KBongPrimary
    }

    val backgroundColor = if (isSelected) teamColor else KBongGrayscaleGray1
    val textColor = if (isSelected) Color.White else KBongGrayscaleGray10

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = teamName, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = textColor)
    }
}

@Preview(showBackground = true)
@Composable
fun KBongButtonPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 기본 버튼
        KBongButton(text = "다음", onClick = {})
        KBongButton(text = "다음", enabled = false, onClick = {})

        // 직관 기록 하기 버튼
        KbongDiaryButton(
            text = "기록하러 가기",
            backgroundColor = KBongGrayscaleGray0,
            textColor = KBongPrimary,
            onClick = {}
        )
        KbongDiaryButton(
            text = "기록하러 가기",
            backgroundColor = KBongTeamHeroes10,
            textColor = KBongTeamHeroes,
            onClick = {}
        )
        KbongDiaryButton(
            text = "기록하러 가기",
            backgroundColor = KBongTeamBears10,
            textColor = KBongTeamBears,
            onClick = {}
        )
        KbongDiaryButton(
            text = "기록하러 가기",
            backgroundColor = KBongTeamGiants10,
            textColor = KBongTeamGiants,
            onClick = {}
        )
        KbongDiaryButton(
            text = "기록하러 가기",
            backgroundColor = KBongTeamEagles10,
            textColor = KBongTeamEagles,
            onClick = {}
        )
        KbongDiaryButton(
            text = "기록하러 가기",
            backgroundColor = KBongTeamTwins10,
            textColor = KBongTeamTwins,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}