package com.berkayyaman.footballapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.berkayyaman.footballapp.presentation.favoriteTeam.FavoriteTeamViewModel
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: FavoriteTeamViewModel by viewModels<FavoriteTeamViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FootballAppTheme {

                val state = viewModel.viewStateFlow.collectAsState()

                println(state.value.teams.size)

                Column {
                    Text(text = "Txt", modifier = Modifier.fillMaxWidth())
                    Text(text = "${state.value.teams.size.toString()} aramanızda adet takım bulundu", modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}
