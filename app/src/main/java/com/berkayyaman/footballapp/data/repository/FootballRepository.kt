package com.berkayyaman.footballapp.data.repository

import com.berkayyaman.footballapp.domain.model.TeamsUIModel
import com.berkayyaman.footballapp.util.Resource

/**
 * Created by berkayyaman on 11,October,2024
 */
interface FootballRepository {
    suspend fun searchTeams(searchString: String): Resource<TeamsUIModel>

}