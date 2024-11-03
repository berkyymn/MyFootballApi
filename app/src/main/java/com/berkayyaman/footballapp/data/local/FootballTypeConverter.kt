package com.berkayyaman.footballapp.data.local

import androidx.room.TypeConverter
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

}