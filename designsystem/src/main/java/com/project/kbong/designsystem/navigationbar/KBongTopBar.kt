package com.project.kbong.designsystem.navigationbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.R
import com.project.kbong.designsystem.theme.KBongTheme
import com.project.kbong.designsystem.theme.KBongTypography

@Composable
fun KBongTopBar(
    modifier: Modifier = Modifier,
    titleText: String = "",
    isBackButton: Boolean = true,
    onClickBackButton: () -> Unit = {},
    leftContent: @Composable RowScope.() -> Unit = {},
    rightContent: @Composable BoxScope.() -> Unit = {},
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
    ) {
        Box(modifier = modifier) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isBackButton) {
                    Image(
                        modifier = Modifier.clickable { onClickBackButton() },
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = "BackArrow"
                    )
                }
                leftContent()
            }

            if (titleText.isNotEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    style = KBongTypography.Heading2,
                    text = titleText
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
            ) {
                rightContent()
            }
        }
    }
}

@Preview
@Composable
private fun PreviewKBongBaseTopBar() {
    KBongTheme {
        KBongTopBar()
    }
}

@Preview
@Composable
private fun PreviewKBongTipTopBar() {
    KBongTheme {
        KBongTopBar(
            leftContent = {
                Text(
                    text = "꿀팁 저장소",
                    style = KBongTypography.Heading2
                )
                Image(
                    painter = painterResource(id = R.drawable.tip_on),
                    contentDescription = ""
                )
            }
        )
    }
}

@Preview
@Composable
private fun PreviewKBongHomeTopBar() {
    KBongTheme {
        KBongTopBar(
            leftContent = {
                Image(
                    modifier = Modifier.size(width = 41.dp, height = 21.dp),
                    painter = painterResource(id = R.drawable.kbong),
                    contentDescription = ""
                )
            },
            isBackButton = false,
            rightContent = {
                Image(
                    painter = painterResource(id = R.drawable.tip_off),
                    contentDescription = ""
                )
            }
        )
    }
}

@Preview
@Composable
private fun PreviewKBongTitleTopBar() {
    KBongTheme {
        KBongTopBar(
            titleText = "3월 23일 토요일"
        )
    }
}

@Preview
@Composable
private fun PreviewKBongPostTopBar() {
    KBongTheme {
        KBongTopBar(
            leftContent = {
                Text(
                    text = "구단 VS 구단"
                )
            },
            rightContent = {
                Text(
                    text = "글 올리기"
                )
            }
        )
    }
}

@Preview
@Composable
private fun PreviewKBongMoreTopBar() {
    KBongTheme {
        KBongTopBar(
            rightContent = {
                Image(
                    painter = painterResource(id = R.drawable.more),
                    contentDescription = ""
                )
            }
        )
    }
}