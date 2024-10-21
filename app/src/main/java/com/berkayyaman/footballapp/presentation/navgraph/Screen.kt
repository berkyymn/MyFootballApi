package com.berkayyaman.footballapp.presentation.navgraph

/**
 * Created by berkayyaman on 21,October,2024
 */
sealed class Screen(val route: String) {

    data object HomeScreen: Screen("homeScreen")
    data object LeagueScreen: Screen("leagueScreen")
    data object PlayersScreen: Screen("playersScreen")

    data object AppInitialization: Screen("appInitialization")
    data object AppNavigation: Screen("appNavigation")

}