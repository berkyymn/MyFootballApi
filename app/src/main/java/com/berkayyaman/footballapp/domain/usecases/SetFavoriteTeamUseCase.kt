package com.berkayyaman.footballapp.domain.usecases

import com.berkayyaman.footballapp.data.repository.FootballRepository
import com.berkayyaman.footballapp.domain.model.TeamUiModel
import javax.inject.Inject

/**
 * Created by berkayyaman on 22,October,2024
 */
class SetFavoriteTeamUseCase @Inject constructor(
    private val footballRepository: FootballRepository
) {

    suspend operator fun invoke(teamUiModel: TeamUiModel){
        footballRepository.upsert(teamUiModel)
    }
}