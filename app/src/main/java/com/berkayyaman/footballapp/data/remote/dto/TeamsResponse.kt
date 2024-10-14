package com.berkayyaman.footballapp.data.remote.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamsResponse(
    @SerializedName("response")
    var response: List<Team?>? = null
):Parcelable, BaseEntity()

@Parcelize
data class Team(
    @SerializedName("team")
    var teamInfo: TeamInfo? = null,
    @SerializedName("venue")
    var venue: Venue? = null
):Parcelable

@Parcelize
data class TeamInfo(
    @SerializedName("code")
    var code: String? = null,
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("founded")
    var founded: Int? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("logo")
    var logo: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("national")
    var national: Boolean? = null
):Parcelable

@Parcelize
data class Venue(
    @SerializedName("address")
    var address: String? = null,
    @SerializedName("capacity")
    var capacity: Int? = null,
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("surface")
    var surface: String? = null
):Parcelable