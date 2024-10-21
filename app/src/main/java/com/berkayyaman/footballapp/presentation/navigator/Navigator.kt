package com.berkayyaman.footballapp.presentation.navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.berkayyaman.footballapp.R
import com.berkayyaman.footballapp.presentation.home.HomeScreen
import com.berkayyaman.footballapp.presentation.league.LeagueScreen
import com.berkayyaman.footballapp.presentation.navgraph.Screen
import com.berkayyaman.footballapp.presentation.players.PlayersScreen

/**
 * Created by berkayyaman on 21,October,2024
 */
@Composable
fun Navigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(R.drawable.home,"Home"),
            BottomNavigationItem(R.drawable.league,"League"),
            BottomNavigationItem(R.drawable.players,"Players")
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = remember( key1 = backStackState) {
        when(backStackState?.destination?.route){
            Screen.HomeScreen.route -> 0
            Screen.LeagueScreen.route -> 1
            Screen.PlayersScreen.route -> 2
            else -> 0
        }
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when(index){
                        0 -> navigateToTab(navController, Screen.HomeScreen.route)
                        1 -> navigateToTab(navController, Screen.LeagueScreen.route)
                        2 -> navigateToTab(navController, Screen.PlayersScreen.route)
                    }
                }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(bottom = it.calculateBottomPadding())
        ){
            composable(
                route = Screen.HomeScreen.route
            ){
                HomeScreen()
            }

            composable(
                route = Screen.LeagueScreen.route
            ){
                LeagueScreen()
            }

            composable(
                route = Screen.PlayersScreen.route
            ){
                PlayersScreen()
            }
        }
    }

}

private fun navigateToTab(navController: NavController, route: String){
    navController.navigate(route = route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }

            restoreState = true
            launchSingleTop = true
        }
    }
}