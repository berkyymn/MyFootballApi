package com.berkayyaman.footballapp.data.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.berkayyaman.footballapp.data.local.FootballDao
import com.berkayyaman.footballapp.data.remote.dto.FixtureInfo
import com.berkayyaman.footballapp.domain.model.FixtureInfoUIModel
import com.berkayyaman.footballapp.domain.model.FixturesUIModel
import com.berkayyaman.footballapp.domain.model.TeamUiModel
import com.berkayyaman.footballapp.domain.model.TeamsUIModel
import com.berkayyaman.footballapp.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by berkayyaman on 11,October,2024
 */
interface FootballRepository {
    suspend fun searchTeams(searchString: String): Resource<TeamsUIModel>

    suspend fun getFixtures(teamId: Int, last: Int? = null, next: Int? = null): Resource<FixturesUIModel>


    suspend fun upsert(teamUiModel: TeamUiModel)
    fun getFavoriteTeam(): Flow<TeamUiModel?>

    suspend fun upsertFixtures(fixtureResponse: List<FixtureInfo>)
    fun getFixturesFromDb(teamId: Int): Flow<List<FixtureInfoUIModel>>

}