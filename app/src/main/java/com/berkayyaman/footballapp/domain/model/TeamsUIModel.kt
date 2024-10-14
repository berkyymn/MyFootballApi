package com.berkayyaman.footballapp.domain.model

import android.os.Parcelable
import com.berkayyaman.footballapp.data.remote.dto.BaseEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by berkayyaman on 11,October,2024
 */

data class TeamsUIModel(
    @SerializedName("response")
    var response: ArrayList<TeamUiModel> = arrayListOf(),
): BaseEntity()

@Parcelize
data class TeamUiModel(
    @SerializedName("team")
    var teamInfo: TeamInfoUiModel = TeamInfoUiModel(),
    @SerializedName("venue")
    var venue: VenueUiModel = VenueUiModel()
): Parcelable

@Parcelize
data class TeamInfoUiModel(
    @SerializedName("code")
    var code: String = "",
    @SerializedName("country")
    var country: String = "",
    @SerializedName("founded")
    var founded: Int = -1,
    @SerializedName("id")
    var id: Int = -1,
    @SerializedName("logo")
    var logo: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("national")
    var national: Boolean = false
): Parcelable

@Parcelize
data class VenueUiModel(
    @SerializedName("address")
    var address: String = "",
    @SerializedName("capacity")
    var capacity: Int = -1,
    @SerializedName("city")
    var city: String = "",
    @SerializedName("id")
    var id: Int = -1,
    @SerializedName("image")
    var image: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("surface")
    var surface: String = ""
): Parcelable