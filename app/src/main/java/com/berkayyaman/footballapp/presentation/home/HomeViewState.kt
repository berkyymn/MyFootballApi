package com.berkayyaman.footballapp.presentation.home

import com.berkayyaman.footballapp.domain.model.FixtureInfoUIModel
import com.berkayyaman.footballapp.domain.model.TeamUiModel

/**
* Created by berkayyaman on 05,November,2024
*/

data class HomeViewState(
    val isLoading: Boolean = false,
    val error: String = "",
    val favoriteTeam: TeamUiModel = TeamUiModel(),
    val fixtures: ArrayList<FixtureInfoUIModel> = arrayListOf()
)