package com.berkayyaman.footballapp.presentation.favoriteTeam

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.berkayyaman.footballapp.presentation.searchbar.SearchBar
import com.berkayyaman.footballapp.presentation.searchbar.SearchEvent
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme
import com.berkayyaman.footballapp.util.Dimens

/**
 * Created by berkayyaman on 11,October,2024
 */

@Composable
fun FavoriteTeamScreen() {
    val viewModel: FavoriteTeamViewModel = hiltViewModel()
    val state = viewModel.viewStateFlow.collectAsState()

    MyScreen(searchEvent = viewModel::onSearchEvent)

}

@Composable
private fun MyScreen(searchEvent: (SearchEvent) -> Unit){

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(Dimens.MediumSpace),
            text = "Favori Takımınızı Seçiniz",
            style = MaterialTheme.typography.bodyLarge
        )

        SearchBar(event = searchEvent)
    }

}

@Preview
@Composable
private fun FavoriteTeamScreenPreview(){
    FootballAppTheme {
        Surface {
            MyScreen(searchEvent = {})
        }
    }
}

