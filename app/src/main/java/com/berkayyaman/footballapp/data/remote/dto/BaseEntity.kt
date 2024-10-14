package com.berkayyaman.footballapp.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by berkayyaman on 11,October,2024
 */

open class BaseEntity(
    @SerializedName("errors")
    var errors: List<Any?>? = null,
    @SerializedName("paging")
    var paging: Paging? = null,
    @SerializedName("results")
    var results: Int? = null
)

data class Paging(
    @SerializedName("current")
    var current: Int?,
    @SerializedName("total")
    var total: Int?
)