package com.project.presentation.my.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.domain.model.user.VisitedGames
import com.project.kbong.designsystem.theme.KBongGrayscaleGray6
import com.project.kbong.designsystem.theme.KBongGrayscaleGray8
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.kbong.designsystem.theme.VisitedCardBackground
import com.project.presentation.R

@Composable
fun VisitedGameContent(
    modifier: Modifier = Modifier,
    visitedGames: VisitedGames
) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        VisitedWinScoreCard(
            modifier = Modifier.weight(1F),
            myTeamWins = visitedGames.myTeamWins,
            myTeamLosses = visitedGames.myTeamLosses,
            myTeamDraws = visitedGames.myTeamDraws
        )
        Spacer(modifier = Modifier.width(8.dp))
        VisitedWinScorePercentCard(
            modifier = Modifier.weight(1F),
            myTeamRate = visitedGames.myTeamRate
        )
    }
}

@Composable
private fun VisitedWinScoreCard(
    modifier: Modifier,
    myTeamWins: Int,
    myTeamLosses: Int,
    myTeamDraws: Int,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(VisitedCardBackground)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_visited_score),
                contentDescription = "ic_visited_score"
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${myTeamWins}승 ${myTeamLosses}패 ${myTeamDraws}무",
                style = KBongTypography.Heading1,
                color = KBongGrayscaleGray8
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.my_team_visited_score),
                style = KBongTypography.Label1Regular,
                color = KBongGrayscaleGray6
            )
        }
    }
}

@Composable
fun VisitedWinScorePercentCard(
    modifier: Modifier,
    myTeamRate: Int
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(VisitedCardBackground)
    ) {
        if (myTeamRate >= 50) {
            Image(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 18.dp),
                painter = painterResource(R.drawable.ic_victory),
                contentDescription = "Victory"
            )
        }

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_visited_graph),
                contentDescription = "ic_visited_graph"
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${myTeamRate}%",
                style = KBongTypography.Heading1,
                color = KBongGrayscaleGray8
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.my_team_visited_percent),
                style = KBongTypography.Label1Regular,
                color = KBongGrayscaleGray6
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFE8F3FF)
@Composable
fun PreviewVisitedGameContent() {
    val visitedGames = VisitedGames()
    VisitedGameContent(
        visitedGames = visitedGames
    )
}