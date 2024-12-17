package com.berkayyaman.footballapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by berkayyaman on 16,December,2024
 */
object SharedFunctions {

    fun timeStampToFormattedDate(timestamp: Int,dateFormat: String = "dd-MM"): String {
        val date = Date(timestamp.toLong() * 1000)
        val format = SimpleDateFormat(dateFormat, Locale.getDefault())
        return format.format(date)
    }
}