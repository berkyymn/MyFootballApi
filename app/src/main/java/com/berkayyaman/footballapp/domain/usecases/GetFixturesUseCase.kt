package com.berkayyaman.footballapp.domain.usecases

import com.berkayyaman.footballapp.data.repository.FootballRepository
import com.berkayyaman.footballapp.domain.model.FixturesUIModel
import com.berkayyaman.footballapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
* Created by berkayyaman on 09,December,2024
*/

class GetFixturesUseCase @Inject constructor(
    private val footballRepository: FootballRepository
) {

    operator fun invoke(teamId: Int): Flow<Resource<FixturesUIModel>> = flow {
        emit(footballRepository.getFixtures(teamId))
    }
}