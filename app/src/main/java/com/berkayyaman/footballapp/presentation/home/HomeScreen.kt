package com.berkayyaman.footballapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.berkayyaman.footballapp.presentation.common.CircularIndicator
import com.berkayyaman.footballapp.presentation.common.ErrorDialog
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme
import com.berkayyaman.footballapp.util.Dimens

/**
 * Created by berkayyaman on 21,October,2024
 */

@Composable
fun HomeScreen() {

    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.viewStateFlow.collectAsState()


    MyView(state.value)


}

@Composable
private fun MyView(state: HomeViewState){
    CircularIndicator(state.isLoading)
    ErrorDialog(state.error)

    Column {
        FavoriteTeamSummaryView(state.favoriteTeam)

        Text(
            modifier = Modifier.padding(Dimens.MediumSpace),
            text = if (state.fixtures.isEmpty()){
                "henüz veri yok"
            }else{
                "${state.fixtures.size} kadar veri var"
            },
            style = MaterialTheme.typography.titleLarge
        )
    }

}

@Preview
@Composable
private fun HomeScreenPreview(){
    FootballAppTheme {
        Surface {
            MyView(
                HomeViewState(
                    fixtures = arrayListOf(),
                    isLoading = false
                )
            )
        }
    }
}