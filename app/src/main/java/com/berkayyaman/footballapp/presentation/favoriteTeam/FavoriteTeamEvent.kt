package com.berkayyaman.footballapp.presentation.favoriteTeam

import com.berkayyaman.footballapp.domain.model.TeamUiModel

/**
 * Created by berkayyaman on 11,October,2024
 */
sealed class FavoriteTeamEvent: FavTeamEventInterface {
    data class TeamClicked(val teamUiModel: TeamUiModel): FavoriteTeamEvent()
}