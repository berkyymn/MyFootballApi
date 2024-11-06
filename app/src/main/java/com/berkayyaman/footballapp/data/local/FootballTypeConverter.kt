package com.berkayyaman.footballapp.data.local

import androidx.room.TypeConverter
import com.berkayyaman.footballapp.data.remote.dto.Fixture
import com.berkayyaman.footballapp.data.remote.dto.Goals
import com.berkayyaman.footballapp.data.remote.dto.League
import com.berkayyaman.footballapp.data.remote.dto.Score
import com.berkayyaman.footballapp.data.remote.dto.Teams
import com.berkayyaman.footballapp.domain.model.TeamInfoUiModel
import com.berkayyaman.footballapp.domain.model.VenueUiModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by berkayyaman on 22,October,2024
 */
class FootballTypeConverter {

    @TypeConverter
    fun teamInfoToString(teamInfo: TeamInfoUiModel): String {
        return Gson().toJson(teamInfo)
    }

    @TypeConverter
    fun stringToTeamInfo(teamInfoString: String): TeamInfoUiModel {
        val type = object : TypeToken<TeamInfoUiModel>() {}.type
        return Gson().fromJson(teamInfoString, type)
    }

    @TypeConverter
    fun venueToString(venue: VenueUiModel): String {
        return Gson().toJson(venue)
    }

    @TypeConverter
    fun stringToVenue(venueString: String): VenueUiModel {
        val type = object : TypeToken<VenueUiModel>() {}.type
        return Gson().fromJson(venueString, type)
    }

    @TypeConverter
    fun leagueToString(league: League): String{
        return Gson().toJson(league)
    }

    @TypeConverter
    fun stringToLeague(string: String): League {
        val type = object: TypeToken<League>(){}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fixtureToString(fixture: Fixture): String{
        return Gson().toJson(fixture)
    }

    @TypeConverter
    fun stringToFixture(string: String): Fixture {
        val type = object: TypeToken<Fixture>(){}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun goalsToString(goals: Goals): String{
        return Gson().toJson(goals)
    }

    @TypeConverter
    fun stringToGoals(string: String): Goals {
        val type = object: TypeToken<Goals>(){}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun scoreToString(score: Score): String{
        return Gson().toJson(score)
    }

    @TypeConverter
    fun stringToScore(string: String): Score {
        val type = object: TypeToken<Score>(){}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun teamsToString(teams: Teams): String{
        return Gson().toJson(teams)
    }

    @TypeConverter
    fun stringToTeams(string: String): Teams {
        val type = object: TypeToken<Teams>(){}.type
        return Gson().fromJson(string, type)
    }

}