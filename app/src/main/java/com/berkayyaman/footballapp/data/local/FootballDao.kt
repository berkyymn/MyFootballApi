package com.berkayyaman.footballapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.berkayyaman.footballapp.data.remote.dto.FixtureInfo
import com.berkayyaman.footballapp.domain.model.TeamUiModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by berkayyaman on 22,October,2024
 */

@Dao
interface FootballDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(teamUiModel: TeamUiModel)

    @Query("SELECT * FROM TeamUiModel")
    fun getFavoriteTeam(): Flow<TeamUiModel?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertFixtures(fixtureResponse: List<FixtureInfo>)

    @Query("SELECT * FROM FixturesResponse where teamId=:teamId")
    fun getFixtureResponse(teamId: Int): Flow<List<FixtureInfo>>
}