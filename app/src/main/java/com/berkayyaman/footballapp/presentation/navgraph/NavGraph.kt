package com.berkayyaman.footballapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.berkayyaman.footballapp.presentation.favoriteTeam.FavoriteTeamScreen
import com.berkayyaman.footballapp.presentation.navigator.Navigator

/**
 * Created by berkayyaman on 21,October,2024
 */

@Composable
fun NavGraph(
    startDestination: String
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        navigation(
            route = "myFirstRoute",
            startDestination = Screen.AppInitialization.route
        ){
            composable(
                route = Screen.AppInitialization.route
            ){
                FavoriteTeamScreen()
            }
        }

        navigation(
            route = "mySecondRoute",
            startDestination = Screen.AppNavigation.route
        ){
            composable(
                route = Screen.AppNavigation.route
            ){
                Navigator()
            }
        }
    }

}