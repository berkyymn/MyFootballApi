package com.berkayyaman.footballapp.domain.repository

import com.berkayyaman.footballapp.data.remote.FootballApi
import com.berkayyaman.footballapp.data.remote.mapper.DTOMapper
import com.berkayyaman.footballapp.data.repository.FootballRepository
import com.berkayyaman.footballapp.domain.model.TeamsUIModel
import com.berkayyaman.footballapp.util.Resource
import com.berkayyaman.footballapp.util.mapOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by berkayyaman on 11,October,2024
 */

class FootballRepositoryIMPL(
    private val api: FootballApi,
    private val dtoMapper: DTOMapper,
) : BaseRepository(), FootballRepository {


    override suspend fun searchTeams(searchString: String): Resource<TeamsUIModel> =
        withContext(Dispatchers.IO) {
            request {
                api.searchForTeam(searchString)
            }.mapOnSuccess {
                dtoMapper.map(it)
            }
        }

}