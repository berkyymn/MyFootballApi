package com.berkayyaman.footballapp.util

import com.berkayyaman.footballapp.domain.model.TeamInfoUiModel
import com.berkayyaman.footballapp.domain.model.TeamUiModel
import com.berkayyaman.footballapp.domain.model.VenueUiModel

/**
 * Created by berkayyaman on 03,October,2024
 */
object MockData {

    val teamUiModel = TeamUiModel(
        TeamInfoUiModel(
            code = "FEN",
            id = 611,
            name = "Fenerbahce",
            country = "Turkey",
            founded = 1907,
            national = false,
            logo = "https://media.api-sports.io/football/teams/611.png"
        ),
        venue = VenueUiModel(
            address = "address",
            capacity = 55502,
            city = "İstanbul",
            id = 611123,
            image = "url",
            name = "Ülker Stadyumu Fenerbahçe Şükrü Saraçoğlu Spor Kompleksi",
            surface = "grass"
        )
    )

}