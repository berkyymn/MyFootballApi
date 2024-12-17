package com.berkayyaman.footballapp.presentation.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.berkayyaman.footballapp.domain.model.FixtureInfoUIModel
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme
import com.berkayyaman.footballapp.util.Dimens
import com.berkayyaman.footballapp.util.MockData
import com.berkayyaman.footballapp.util.SharedFunctions

/**
 * Created by berkayyaman on 16,December,2024
 */

@Composable
fun MatchSummaryItem(
    modifier: Modifier,
    fixtureInfoUIModel: FixtureInfoUIModel
) {

    MyView(modifier = modifier, fixture = fixtureInfoUIModel)
}

@Composable
private fun MyView(modifier: Modifier,fixture: FixtureInfoUIModel){

    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
            .padding(top = Dimens.MiniSpace, bottom = Dimens.MiniSpace)
    ) {
        val (statusText, homeTeamText, homeTeamLogo,scoreText, awayTeamLogo, awayTeamText) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(statusText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                },
            textAlign = TextAlign.Center,
            text = SharedFunctions.timeStampToFormattedDate(fixture.fixture.timestamp),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = if (fixture.fixture.status.long == FixtureStatusEnum.TIME_TO_BE_DEFINED.text)
                FontWeight.Bold else FontWeight.W400,

        )

        Text(
            modifier = Modifier.constrainAs(homeTeamText){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(homeTeamLogo.start)
                start.linkTo(statusText.end)
                width = Dimension.fillToConstraints
            },
            text = fixture.teams.home.name,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )

        AsyncImage(
            modifier = Modifier.constrainAs(homeTeamLogo){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(scoreText.start)
            }
                .size(Dimens.TeamLogoRowSize*3/4)
                .clip(MaterialTheme.shapes.small),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(fixture.teams.home.logo)
                .build(),
            contentDescription = fixture.teams.home.name,
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier.constrainAs(scoreText){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
                .padding(start = Dimens.VeryMiniSpace, end = Dimens.VeryMiniSpace),
            textAlign = TextAlign.Center,
            text = "${fixture.goals.home} - ${fixture.goals.away}"
        )

        AsyncImage(
            modifier = Modifier.constrainAs(awayTeamLogo){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(scoreText.end)
            }
                .size(Dimens.TeamLogoRowSize * 3 / 4)
                .clip(MaterialTheme.shapes.small),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(fixture.teams.away.logo)
                .build(),
            contentDescription = fixture.teams.away.name,
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier.constrainAs(awayTeamText){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(awayTeamLogo.end)
                width = Dimension.fillToConstraints
            },
            text = fixture.teams.away.name,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = if (fixture.teams.away.winner) FontWeight.Bold else FontWeight.W400,
            textAlign = TextAlign.Center
        )

    }


}

@Preview
@Composable
private fun FixtureItemRowPreview(){
    FootballAppTheme {
        Surface {
            MyView(
                modifier = Modifier,
                fixture = MockData.finishedSuperLeagueFixtureInfoUIModel
            )
        }
    }
}

enum class FixtureStatusEnum(val text: String){
    MATCH_FINISHED("Match Finished"),NOT_STARTED("Not Started"),TIME_TO_BE_DEFINED("Time to be defined")
}