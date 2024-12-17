package com.berkayyaman.footballapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.berkayyaman.footballapp.domain.model.FixtureInfoUIModel
import com.berkayyaman.footballapp.presentation.common.CircularIndicator
import com.berkayyaman.footballapp.presentation.common.ErrorDialog
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme
import com.berkayyaman.footballapp.util.Dimens
import com.berkayyaman.footballapp.util.MockData

/**
 * Created by berkayyaman on 21,October,2024
 */

@Composable
fun HomeScreen() {

    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.viewStateFlow.collectAsState()


    MyView(state = state.value)
}

@Composable
private fun MyView(state: HomeViewState){

    CircularIndicator(state.loading)
    ErrorDialog(state.error)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)
        ) {
            FavoriteTeamSummaryView(state.favoriteTeam)

            PreviousAndNextFixtures(state.fixtures)
        }
    }
}



@Composable
private fun PreviousAndNextFixtures(fixtures: ArrayList<FixtureInfoUIModel>){

    var finishedMatchesVisibility by remember {
        mutableStateOf(true)
    }

    var notStartedMatchesVisibility by remember {
        mutableStateOf(true)
    }

    val finishedMatches = fixtures.filter { fixture -> fixture.fixture.status.long == FixtureStatusEnum.MATCH_FINISHED.text }
        .sortedByDescending { it.fixture.date }

    val notStartedMatches = fixtures.filterNot { fixture -> fixture.fixture.status.long == FixtureStatusEnum.MATCH_FINISHED.text }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (finishedMatches.isNotEmpty()){
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .clickable {
                        finishedMatchesVisibility = !finishedMatchesVisibility
                    }
            ){
                Text(
                    modifier = Modifier.padding(Dimens.MiniSpace),
                    text =  "Finished Matches",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            if (finishedMatchesVisibility){

                finishedMatches.groupBy { fixture ->
                    fixture.league.id
                }.values.forEach{ fixtureList ->

                    MatchSummariesWithLeagueLabelView(
                        fixtures = java.util.ArrayList(fixtureList),
                        league = fixtureList[0].league
                    ) {
                        // click eventi
                    }

                }
            }

        }

        if (notStartedMatches.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary
                    )
                    .fillMaxWidth()
                    .clickable {
                        notStartedMatchesVisibility = !notStartedMatchesVisibility
                    }
            ) {
                Text(
                    modifier = Modifier
                        .padding(Dimens.MiniSpace),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    text = "Upcoming Matches"
                )
            }

            if (notStartedMatchesVisibility) {

                notStartedMatches.groupBy { fixture ->
                    fixture.league.id
                }.values.forEach { fixturesList ->
                    MatchSummariesWithLeagueLabelView(
                        fixtures = java.util.ArrayList(fixturesList),
                        league = fixturesList[0].league,
                    ) {
                        //click eventi
                    }

                }

            }
        }

    }

}


@Preview
@Composable
private fun HomeScreenPreview() {
    FootballAppTheme {
        Surface {
            MyView(
                HomeViewState(
                    loading = false,
                    error = "",
                    favoriteTeam = MockData.teamUiModel,
                    fixtures = arrayListOf<FixtureInfoUIModel>().apply {
                        for (i in 1..2) {
                            add(MockData.finishedEuropeMatchFixtureInfoUiModel)
                            add(MockData.notStartedEuropeMatchFixtureInfoUiModel)
                            add(MockData.notStartedSuperLigMatchFixtureInfoUiModel)
                            add(MockData.finishedSuperLeagueFixtureInfoUIModel)
                        }
                    }
                )
            )
        }
    }

}