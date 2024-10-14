package com.berkayyaman.footballapp.domain.usecases

import com.berkayyaman.footballapp.data.repository.FootballRepository
import com.berkayyaman.footballapp.domain.model.TeamsUIModel
import com.berkayyaman.footballapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by berkayyaman on 11,October,2024
 */
class SearchTeamsUseCase @Inject constructor(private val footballRepository: FootballRepository) {

    operator fun invoke(searchString: String): Flow<Resource<TeamsUIModel>> = flow {
        emit(footballRepository.searchTeams(searchString))
    }
}