package com.berkayyaman.footballapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.berkayyaman.footballapp.presentation.navgraph.NavGraph
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state = viewModel.viewStateFlow.collectAsState()

            FootballAppTheme {
                if (state.value.destination.isNotEmpty()){
                    NavGraph(
                        startDestination = state.value.destination
                    )
                }

            }
        }
    }
}
