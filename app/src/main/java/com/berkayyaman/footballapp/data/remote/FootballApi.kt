package com.berkayyaman.footballapp.data.remote

import com.berkayyaman.footballapp.data.remote.dto.FixturesResponse
import com.berkayyaman.footballapp.data.remote.dto.TeamsResponse
import com.berkayyaman.footballapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by berkayyaman on 11,October,2024
 */
interface FootballApi {

    @Headers("x-rapidapi-key:${Constants.API_KEY}")
    @GET("teams")
    suspend fun searchForTeam(
        @Query("search") searchString: String
    ): Response<TeamsResponse>

    @Headers("x-rapidapi-key:${Constants.API_KEY}")
    @GET("fixtures")
    suspend fun getFixtures(
        @Query("team") teamId: Int,
        @Query("season") season: Int = 2022
    ): Response<FixturesResponse>
}