package com.berkayyaman.footballapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.berkayyaman.footballapp.R
import com.berkayyaman.footballapp.domain.model.FixtureInfoUIModel
import com.berkayyaman.footballapp.domain.model.LeagueUIModel
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme
import com.berkayyaman.footballapp.util.Dimens
import com.berkayyaman.footballapp.util.MockData
import org.jetbrains.annotations.Async
import java.util.Locale

/**
 * Created by berkayyaman on 16,December,2024
 */

@Composable
fun MatchSummariesWithLeagueLabelView(
    fixtures: ArrayList<FixtureInfoUIModel>,
    league: LeagueUIModel,
    fixtureItemClicked: (FixtureInfoUIModel) -> Unit
) {

    MyView(
        fixtures = fixtures,
        league = league,
        fixtureItemClicked = fixtureItemClicked
    )
}

@Composable
private fun MyView(
    fixtures: ArrayList<FixtureInfoUIModel>,
    league: LeagueUIModel,
    fixtureItemClicked: (FixtureInfoUIModel) -> Unit
){

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                modifier = Modifier
                    .padding(Dimens.VeryMiniSpace)
                    .size(
                        width = Dimens.TeamLogoRowSize,
                        height = Dimens.TeamLogoRowSize*3/4
                    )
                    .clip(MaterialTheme.shapes.small),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(league.flag)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                error = painterResource(R.drawable.world_icon),
                contentDescription = null
            )

            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = Dimens.SmallSpace),
                text = "${league.name} - ${league.country}",
                style = MaterialTheme.typography.bodyMedium
            )

            AsyncImage(
                modifier = Modifier
                    .padding(Dimens.VeryMiniSpace)
                    .size(
                        width = Dimens.TeamLogoRowSize,
                        height = Dimens.TeamLogoRowSize*3/4
                    )
                    .clip(MaterialTheme.shapes.small),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(league.logo)
                    .build(),
                error = painterResource(R.drawable.world_icon),
                contentDescription = null
            )

        }


        Column(
            modifier = Modifier.padding(
                start = Dimens.VeryMiniSpace,
                end = Dimens.VeryMiniSpace
            )
        ) {
            fixtures.forEachIndexed{ index, fixtureInfoUIModel ->

                MatchSummaryItem(
                    modifier = Modifier
                        .clickable {
                            fixtureItemClicked(fixtureInfoUIModel)
                        }
                        .background(
                            color = if (index % 2 == 0) Color.Transparent else colorResource(R.color.lightGray)
                        ),
                    fixtureInfoUIModel = fixtureInfoUIModel
                )

            }
        }
    }
}

@Preview
@Composable
private fun FixtureItemRowPreview(){
    FootballAppTheme {
        Surface {
            MyView(
                league = MockData.finishedSuperLeagueFixtureInfoUIModel.league,
                fixtures = arrayListOf<FixtureInfoUIModel>().apply {
                    for (i in 1..5){
                        add(MockData.finishedSuperLeagueFixtureInfoUIModel)
                    }
                }
            ) { }
        }
    }
}