package com.berkayyaman.footballapp.presentation.home

import com.berkayyaman.footballapp.domain.model.FixtureInfoUIModel
import com.berkayyaman.footballapp.domain.model.TeamUiModel

/**
* Created by berkayyaman on 09,December,2024
*/

data class HomeViewState (
    val loading: Boolean = false,
    val favoriteTeam: TeamUiModel = TeamUiModel(),
    val error: String = "",
    val fixtures: ArrayList<FixtureInfoUIModel> = arrayListOf()
)