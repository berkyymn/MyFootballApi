package com.berkayyaman.footballapp.presentation.favoriteTeam

import com.berkayyaman.footballapp.domain.model.TeamUiModel

/**
 * Created by berkayyaman on 11,October,2024
 */
data class FavoriteTeamViewState(
    val teams: ArrayList<TeamUiModel> = arrayListOf(),
    val error: String = "",
    var searchString: String = "",
    var isLoading: Boolean = false
)
