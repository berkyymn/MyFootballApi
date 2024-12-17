package com.berkayyaman.footballapp.domain.repository

import com.berkayyaman.footballapp.data.local.FootballDao
import com.berkayyaman.footballapp.data.remote.FootballApi
import com.berkayyaman.footballapp.data.remote.dto.FixtureInfo
import com.berkayyaman.footballapp.data.remote.mapper.DTOMapper
import com.berkayyaman.footballapp.data.repository.FootballRepository
import com.berkayyaman.footballapp.domain.model.FixtureInfoUIModel
import com.berkayyaman.footballapp.domain.model.FixturesUIModel
import com.berkayyaman.footballapp.domain.model.TeamUiModel
import com.berkayyaman.footballapp.domain.model.TeamsUIModel
import com.berkayyaman.footballapp.util.Resource
import com.berkayyaman.footballapp.util.mapOnSuccess
import com.berkayyaman.footballapp.util.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Created by berkayyaman on 11,October,2024
 */

class FootballRepositoryIMPL(
    private val api: FootballApi,
    private val footballDao: FootballDao,
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

    override suspend fun getFixtures(teamId: Int): Resource<FixturesUIModel> =
        withContext(Dispatchers.IO) {
            request {
                api.getFixtures(teamId)
            }.onSuccess { fixturesResponse ->

                val list = ArrayList(fixturesResponse.response?: arrayListOf()).filterNotNull()
                    .filterNot { fixtureInfo -> fixtureInfo.fixture?.id == null }

                list.forEach{
                    it.teamId = teamId
                    it.fixtureId = it.fixture!!.id!!
                }

                upsertFixtures(fixturesResponse = list)

            }.mapOnSuccess {
                dtoMapper.map(it)
            }
        }


    override suspend fun upsertFavoriteTeam(teamUiModel: TeamUiModel) {
        footballDao.upsert(teamUiModel)
    }

    override fun getFavoriteTeam(): Flow<TeamUiModel?> {
        return footballDao.getFavoriteTeam()
    }

    override suspend fun upsertFixtures(fixturesResponse: List<FixtureInfo>) {
        footballDao.upsertFixtures(fixturesResponse)
    }

    override fun getFixturesFromDB(teamId: Int): Flow<List<FixtureInfoUIModel>> {
        return footballDao.getFixturesResponse(teamId).map {
            it.map { item ->
                dtoMapper.map(item)
            }
        }
    }


}