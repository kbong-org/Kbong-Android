package com.project.presentation.my.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.domain.model.user.VisitedGames
import com.project.kbong.designsystem.theme.KBongGrayscaleGray9
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R
import com.project.presentation.my.MyTeamType

@Composable
fun MyTopContent(
    modifier: Modifier = Modifier,
    myTeamType: MyTeamType,
    nickname: String,
    visitedGames: VisitedGames,
) {
    val stadiumImage = if (myTeamType.isBlueStadium) {
        painterResource(R.drawable.ic_blue_stadium)
    } else {
        painterResource(R.drawable.ic_red_stadium)
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Box() {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    TeamTag(
                        backgroundColor = myTeamType.teamTagBackgroundColor,
                        name = myTeamType.teamName
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${nickname}님\n총 ${visitedGames.total}번 직관 중!",
                        style = KBongTypography.Title,
                        color = KBongGrayscaleGray9
                    )
                }
                Image(
                    modifier = Modifier.offset(x = (-6).dp, y = (-9).dp),
                    painter = stadiumImage,
                    contentDescription = "stadiumImage"
                )
            }
        }
    }
}

@Composable
private fun TeamTag(
    backgroundColor: Color,
    name: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
            text = name,
            color = Color.White,
            style = KBongTypography.Caption1
        )
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewMyTopContent() {
    MyTopContent(
        myTeamType = MyTeamType.KIA,
        nickname = "지게",
        visitedGames = VisitedGames(total = 23)
    )
}