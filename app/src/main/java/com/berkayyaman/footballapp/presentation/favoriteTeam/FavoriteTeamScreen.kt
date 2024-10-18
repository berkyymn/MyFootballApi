package com.berkayyaman.footballapp.presentation.favoriteTeam

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.berkayyaman.footballapp.domain.model.TeamUiModel
import com.berkayyaman.footballapp.presentation.searchbar.SearchBar
import com.berkayyaman.footballapp.presentation.searchbar.SearchEvent
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme
import com.berkayyaman.footballapp.util.Dimens
import com.berkayyaman.footballapp.util.MockData

/**
 * Created by berkayyaman on 11,October,2024
 */

@Composable
fun FavoriteTeamScreen() {
    val viewModel: FavoriteTeamViewModel = hiltViewModel()
    val state = viewModel.viewStateFlow.collectAsState()

    MyScreen(onEvent = viewModel::onEvent, state = state.value)

}

@Composable
private fun MyScreen(
    onEvent: (FavTeamEventInterface) -> Unit,
    state: FavoriteTeamViewState
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.MediumSpace),
            text = "Favori Takımınızı Seçiniz",
            style = MaterialTheme.typography.bodyLarge
        )

        SearchBar(event = onEvent)
        TeamList(teamList = state.teams, event = onEvent )
    }

}

@Composable
fun TeamList(
    teamList: ArrayList<TeamUiModel>,
    event: (FavoriteTeamEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(Dimens.SmallSpace)
    ) {
        items(teamList) { item ->
            FavoriteTeamInfoRow(
                teamUiModel = item,
                event = event
            )
        }
    }
}

@Preview
@Composable
private fun FavoriteTeamScreenPreview() {
    FootballAppTheme {
        Surface {
            MyScreen(
                state = FavoriteTeamViewState(
                    teams = arrayListOf<TeamUiModel>().apply {
                        for (i in 1..5){
                            add(MockData.teamUiModel)
                        }
                    }
                ),
                onEvent = {}
            )
        }
    }
}

