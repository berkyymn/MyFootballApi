package com.berkayyaman.footballapp.domain.model

import android.os.Parcelable
import com.berkayyaman.footballapp.data.remote.dto.BaseEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by berkayyaman on 23,October,2024
 */
data class FixturesUIModel(
    @SerializedName("response")
    var response: ArrayList<FixtureInfoUIModel> = arrayListOf(),
): BaseEntity()

@Parcelize
data class FixtureInfoUIModel(
    @SerializedName("fixture")
    var fixture: FixtureUIModel = FixtureUIModel(),
    @SerializedName("goals")
    var goals: GoalsUIModel = GoalsUIModel(),
    @SerializedName("league")
    var league: LeagueUIModel = LeagueUIModel(),
    @SerializedName("score")
    var score: ScoreUIModel = ScoreUIModel(),
    @SerializedName("teams")
    var teams: FixtureTeamsUIModel = FixtureTeamsUIModel()
): Parcelable

@Parcelize
data class FixtureUIModel(
    @SerializedName("date")
    var date: String = "",
    @SerializedName("id")
    var id: Int = -1,
    @SerializedName("periods")
    var periods: PeriodsUIModel = PeriodsUIModel(),
    @SerializedName("referee")
    var referee: String = "",
    @SerializedName("status")
    var status: StatusUIModel = StatusUIModel(),
    @SerializedName("timestamp")
    var timestamp: Int = -1,
    @SerializedName("timezone")
    var timezone: String = "",
    @SerializedName("venue")
    var fixtureVenue: FixtureVenueUIModel = FixtureVenueUIModel()
): Parcelable

@Parcelize
data class GoalsUIModel(
    @SerializedName("away")
    var away: String = "V",
    @SerializedName("home")
    var home: String = "V"
): Parcelable



@Parcelize
data class LeagueUIModel(
    @SerializedName("country")
    var country: String = "",
    @SerializedName("flag")
    var flag: String = "",
    @SerializedName("id")
    var id: Int = -1,
    @SerializedName("logo")
    var logo: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("round")
    var round: String = "",
    @SerializedName("season")
    var season: Int = -1
): Parcelable

@Parcelize
data class PeriodsUIModel(
    @SerializedName("first")
    var first: Int? = null,
    @SerializedName("second")
    var second: Int? = null
): Parcelable


@Parcelize
data class ScoreUIModel(
    @SerializedName("extratime")
    var extratime: GoalsUIModel = GoalsUIModel(),
    @SerializedName("fulltime")
    var fulltime: GoalsUIModel = GoalsUIModel(),
    @SerializedName("halftime")
    var halftime: GoalsUIModel = GoalsUIModel(),
    @SerializedName("penalty")
    var penalty: GoalsUIModel = GoalsUIModel(),
): Parcelable

@Parcelize
data class StatusUIModel(
    @SerializedName("elapsed")
    var elapsed: Int = -1,
    @SerializedName("extra")
    var extra: Int = -1,
    @SerializedName("long")
    var long: String = "",
    @SerializedName("short")
    var short: String = ""
): Parcelable

@Parcelize
data class FixtureTeamsUIModel(
    @SerializedName("away")
    var away: FixtureTeamsInfoUIModel = FixtureTeamsInfoUIModel(),
    @SerializedName("home")
    var home: FixtureTeamsInfoUIModel = FixtureTeamsInfoUIModel()
): Parcelable

@Parcelize
data class FixtureTeamsInfoUIModel(
    @SerializedName("id")
    var id: Int = -1,
    @SerializedName("logo")
    var logo: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("winner")
    var winner: Boolean = false
): Parcelable


@Parcelize
data class FixtureVenueUIModel(
    @SerializedName("city")
    var city: String = "",
    @SerializedName("id")
    var id: Int = -1,
    @SerializedName("name")
    var name: String = ""
): Parcelable