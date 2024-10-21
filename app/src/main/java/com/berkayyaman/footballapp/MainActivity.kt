package com.berkayyaman.footballapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.berkayyaman.footballapp.presentation.navgraph.NavGraph
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FootballAppTheme {
                NavGraph(
                    startDestination = "mySecondRoute"
                )
            }
        }
    }
}
