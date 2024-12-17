package com.berkayyaman.footballapp.data.remote.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class FixturesResponse(
    @SerializedName("response")
    var response: ArrayList<FixtureInfo?>? = null,
):BaseEntity()

@Entity(tableName = "FixturesResponse")
@Parcelize
data class FixtureInfo(
    @PrimaryKey
    var fixtureId: Int,
    var teamId: Int,
    @SerializedName("fixture")
    var fixture: Fixture? = null,
    @SerializedName("goals")
    var goals: Goals? = null,
    @SerializedName("league")
    var league: League? = null,
    @SerializedName("score")
    var score: Score? = null,
    @SerializedName("teams")
    var teams: Teams? = null
):Parcelable
@Parcelize
data class Away(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("logo")
    var logo: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("winner")
    var winner: Boolean? = null
):Parcelable

@Parcelize
data class Fixture(
    @SerializedName("date")
    var date: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("periods")
    var periods: Periods? = null,
    @SerializedName("referee")
    var referee: String? = null,
    @SerializedName("status")
    var status: Status? = null,
    @SerializedName("timestamp")
    var timestamp: Int? = null,
    @SerializedName("timezone")
    var timezone: String? = null,
    @SerializedName("venue")
    var fixtureVenue: FixtureVenue? = null
):Parcelable

@Parcelize
data class Goals(
    @SerializedName("away")
    var away: Int? = null,
    @SerializedName("home")
    var home: Int? = null
):Parcelable

@Parcelize
data class Home(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("logo")
    var logo: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("winner")
    var winner: Boolean? = null
):Parcelable

@Parcelize
data class League(
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("flag")
    var flag: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("logo")
    var logo: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("round")
    var round: String? = null,
    @SerializedName("season")
    var season: Int? = null
):Parcelable

@Parcelize
data class Periods(
    @SerializedName("first")
    var first: Int? = null,
    @SerializedName("second")
    var second: Int? = null
):Parcelable


@Parcelize
data class Score(
    @SerializedName("extratime")
    var extratime: Goals? = null,
    @SerializedName("fulltime")
    var fulltime: Goals? = null,
    @SerializedName("halftime")
    var halftime: Goals? = null,
    @SerializedName("penalty")
    var penalty: Goals? = null
):Parcelable

@Parcelize
data class Status(
    @SerializedName("elapsed")
    var elapsed: Int? = null,
    @SerializedName("extra")
    var extra: Int? = null,
    @SerializedName("long")
    var long: String? = null,
    @SerializedName("short")
    var short: String? = null
):Parcelable

@Parcelize
data class Teams(
    @SerializedName("away")
    var away: Away? = null,
    @SerializedName("home")
    var home: Home? = null
):Parcelable


@Parcelize
data class FixtureVenue(
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null
):Parcelable