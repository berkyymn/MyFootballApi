package com.berkayyaman.footballapp.util

import com.berkayyaman.footballapp.domain.model.FixtureInfoUIModel
import com.berkayyaman.footballapp.domain.model.FixtureTeamsInfoUIModel
import com.berkayyaman.footballapp.domain.model.FixtureTeamsUIModel
import com.berkayyaman.footballapp.domain.model.FixtureUIModel
import com.berkayyaman.footballapp.domain.model.GoalsUIModel
import com.berkayyaman.footballapp.domain.model.LeagueUIModel
import com.berkayyaman.footballapp.domain.model.StatusUIModel
import com.berkayyaman.footballapp.domain.model.TeamInfoUiModel
import com.berkayyaman.footballapp.domain.model.TeamUiModel
import com.berkayyaman.footballapp.domain.model.VenueUiModel

/**
 * Created by berkayyaman on 03,October,2024
 */
object MockData {

    val teamUiModel = TeamUiModel(
        id = 0,
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

    val finishedSuperLeagueFixtureInfoUIModel = FixtureInfoUIModel(
        fixture = FixtureUIModel(
            timezone = "UTC",
            date = "2024-10-27T15:00:00+00:00",
            timestamp = 1730041200,
            status = StatusUIModel(
                long = "Match Finished",
                short = "FT",
            )
        ),
        goals = GoalsUIModel(
            home = "2",
            away = "0"
        ),
        league = LeagueUIModel(
            id = 203,
            name = "Süper Lig",
            country = "Turkey",
        ),
        teams = FixtureTeamsUIModel(
            home = FixtureTeamsInfoUIModel(
                name = "Fenerbahce",
                winner = true
            ),
            away = FixtureTeamsInfoUIModel(
                name = "BB Bodrumspor",
                winner = false
            )
        )
    )


    val notStartedSuperLigMatchFixtureInfoUiModel = FixtureInfoUIModel(
        fixture = FixtureUIModel(
            date = "2024-11-03T16:00:00+00:00",
            timestamp = 1730649600,
            status = StatusUIModel(
                long = "Not Started",
                short = "NS",
            )
        ),
        goals = GoalsUIModel(
            home = "V",
            away = "V"
        ),
        league = LeagueUIModel(
            id = 203,
            name = "Süper Lig",
            country = "Turkey",
        ),
        teams = FixtureTeamsUIModel(
            home = FixtureTeamsInfoUIModel(
                name = "Trabzonspor",
                winner = false
            ),
            away = FixtureTeamsInfoUIModel(
                name = "Fenerbahçe",
                winner = false
            )
        )
    )

    val finishedEuropeMatchFixtureInfoUiModel = FixtureInfoUIModel(
        fixture = FixtureUIModel(
            timezone = "UTC",
            date = "2024-10-24T19:00:00+00:00",
            timestamp = 1729796400,
            status = StatusUIModel(
                long = "Match Finished",
                short = "FT",
            )
        ),
        goals = GoalsUIModel(
            home = "1",
            away = "1"
        ),
        league = LeagueUIModel(
            id = 3,
            name = "UEFA Europa League",
            country = "World",
        ),
        teams = FixtureTeamsUIModel(
            home = FixtureTeamsInfoUIModel(
                name = "Fenerbahce",
                winner = false
            ),
            away = FixtureTeamsInfoUIModel(
                name = "Manchester United",
                winner = false
            )
        )
    )

    val notStartedEuropeMatchFixtureInfoUiModel = FixtureInfoUIModel(
        fixture = FixtureUIModel(
            date = "2024-11-07T20:00:00+00:00",
            timestamp = 1731009600,
            status = StatusUIModel(
                long = "Not Started",
                short = "NS",
            )
        ),
        league = LeagueUIModel(
            id = 3,
            name = "UEFA Europa League",
            country = "World"
        ),
        teams = FixtureTeamsUIModel(
            home = FixtureTeamsInfoUIModel(
                name = "AZ Alkmaar",
                winner = false
            ),
            away = FixtureTeamsInfoUIModel(
                name = "Fenerbahçe",
                winner = false
            )
        )
    )

}