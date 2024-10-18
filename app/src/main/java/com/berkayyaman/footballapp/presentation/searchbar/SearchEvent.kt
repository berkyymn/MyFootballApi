package com.berkayyaman.footballapp.presentation.searchbar

import com.berkayyaman.footballapp.presentation.favoriteTeam.FavTeamEventInterface

/**
 * Created by berkayyaman on 14,October,2024
 */

sealed class SearchEvent: FavTeamEventInterface {
    data object Search: SearchEvent()
    data class UpdateSearchString(val searchString: String): SearchEvent()
}