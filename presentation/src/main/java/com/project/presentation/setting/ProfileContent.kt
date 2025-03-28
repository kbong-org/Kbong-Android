package com.project.presentation.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.component.BaseSettingContent
import com.project.kbong.designsystem.tag.KBongTag
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.my.MyTeamType

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    nickName: String,
    myTeamType: MyTeamType,
) {
    BaseSettingContent(
        modifier = modifier,
        leftContent = {
            Text(
                text = nickName,
                style = KBongTypography.Heading1,
                color = KBongGrayscaleGray8
            )
            Spacer(modifier = Modifier.width(8.dp))
            KBongTag(
                tagName = myTeamType.teamName,
                backgroundColor = myTeamType.teamSub10Color,
                textColor = myTeamType.teamTagBackgroundColor
            )
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewProfileContent() {
    ProfileContent(
        modifier = Modifier.fillMaxWidth(),
        nickName = "지게",
        myTeamType = MyTeamType.SSG,
    )
}