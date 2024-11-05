package com.berkayyaman.footballapp.data.repository

import com.berkayyaman.footballapp.data.local.FootballDao
import com.berkayyaman.footballapp.domain.model.FixturesUIModel
import com.berkayyaman.footballapp.domain.model.TeamsUIModel
import com.berkayyaman.footballapp.util.Resource

/**
 * Created by berkayyaman on 11,October,2024
 */
interface FootballRepository: FootballDao {
    suspend fun searchTeams(searchString: String): Resource<TeamsUIModel>

    suspend fun getFixtures(teamId: Int, last: Int? = null, next: Int? = null): Resource<FixturesUIModel>

}