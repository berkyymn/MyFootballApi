package com.berkayyaman.footballapp.presentation.searchbar

/**
 * Created by berkayyaman on 14,October,2024
 */

sealed class SearchEvent {
    data object Search: SearchEvent()
    data class UpdateSearchString(val searchString: String): SearchEvent()
}