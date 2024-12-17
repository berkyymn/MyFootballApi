package com.berkayyaman.footballapp.data.remote.mapper

import com.berkayyaman.footballapp.data.remote.dto.FixtureInfo
import com.berkayyaman.footballapp.data.remote.dto.FixturesResponse
import com.berkayyaman.footballapp.data.remote.dto.TeamInfo
import com.berkayyaman.footballapp.data.remote.dto.TeamsResponse
import com.berkayyaman.footballapp.domain.model.FixtureInfoUIModel
import com.berkayyaman.footballapp.domain.model.FixtureTeamsInfoUIModel
import com.berkayyaman.footballapp.domain.model.FixtureTeamsUIModel
import com.berkayyaman.footballapp.domain.model.FixtureUIModel
import com.berkayyaman.footballapp.domain.model.FixtureVenueUIModel
import com.berkayyaman.footballapp.domain.model.FixturesUIModel
import com.berkayyaman.footballapp.domain.model.GoalsUIModel
import com.berkayyaman.footballapp.domain.model.LeagueUIModel
import com.berkayyaman.footballapp.domain.model.PeriodsUIModel
import com.berkayyaman.footballapp.domain.model.ScoreUIModel
import com.berkayyaman.footballapp.domain.model.StatusUIModel
import com.berkayyaman.footballapp.domain.model.TeamInfoUiModel
import com.berkayyaman.footballapp.domain.model.TeamUiModel
import com.berkayyaman.footballapp.domain.model.TeamsUIModel
import com.berkayyaman.footballapp.domain.model.VenueUiModel

/**
 * Created by berkayyaman on 11,October,2024
 */
class DTOMapper {

    fun map(teamsResponse: TeamsResponse?): TeamsUIModel {
        return TeamsUIModel(
            response = teamsResponse?.response?.map {
                TeamUiModel(
                    teamInfo = TeamInfoUiModel(
                        code = it?.teamInfo?.code.orEmpty(),
                        country = it?.teamInfo?.country.orEmpty(),
                        founded = it?.teamInfo?.founded ?: -1,
                        id = it?.teamInfo?.id ?: -1,
                        logo = it?.teamInfo?.logo.orEmpty(),
                        name = it?.teamInfo?.name.orEmpty(),
                        national = it?.teamInfo?.national ?: false
                    ),
                    venue = VenueUiModel(
                        address = it?.venue?.address.orEmpty(),
                        capacity = it?.venue?.capacity ?: -1,
                        city = it?.venue?.city.orEmpty(),
                        id = it?.venue?.id ?: -1,
                        image = it?.venue?.image.orEmpty(),
                        name = it?.venue?.name.orEmpty(),
                    )
                )
            }?.toCollection(ArrayList()) ?: arrayListOf()
        )
    }

    fun map(fixturesResponse: FixturesResponse?): FixturesUIModel {
        return FixturesUIModel(
            response = fixturesResponse?.response?.map {
                map(fixtureInfo = it)
            }?.toCollection(ArrayList()) ?: arrayListOf()
        )
    }

    fun map(fixtureInfo: FixtureInfo?): FixtureInfoUIModel {

        return FixtureInfoUIModel(
            fixture = FixtureUIModel(
                date = fixtureInfo?.fixture?.date.orEmpty(),
                id = fixtureInfo?.fixture?.id ?: -1,
                periods = PeriodsUIModel(
                    first = fixtureInfo?.fixture?.periods?.first ?: -1,
                    second = fixtureInfo?.fixture?.periods?.second ?: -1
                ),
                referee = fixtureInfo?.fixture?.referee.orEmpty(),
                timestamp = fixtureInfo?.fixture?.timestamp ?: -1,
                timezone = fixtureInfo?.fixture?.timezone.orEmpty(),
                status = StatusUIModel(
                    elapsed = fixtureInfo?.fixture?.status?.elapsed ?: -1,
                    extra = fixtureInfo?.fixture?.status?.extra ?: -1,
                    long = fixtureInfo?.fixture?.status?.long.orEmpty(),
                    short = fixtureInfo?.fixture?.status?.short.orEmpty()
                ),
                fixtureVenue = FixtureVenueUIModel(
                    city = fixtureInfo?.fixture?.fixtureVenue?.city.orEmpty(),
                    id = fixtureInfo?.fixture?.fixtureVenue?.id ?: -1,
                    name = fixtureInfo?.fixture?.fixtureVenue?.name.orEmpty()
                ),
            ),
            goals = GoalsUIModel(
                home = fixtureInfo?.goals?.home?.toString() ?: "V",
                away = fixtureInfo?.goals?.away?.toString() ?: "V"
            ),
            league = LeagueUIModel(
                country = fixtureInfo?.league?.country.orEmpty(),
                flag = fixtureInfo?.league?.flag.orEmpty(),
                id = fixtureInfo?.league?.id ?: -1,
                logo = fixtureInfo?.league?.logo.orEmpty(),
                name = fixtureInfo?.league?.name.orEmpty(),
                round = fixtureInfo?.league?.round.orEmpty(),
                season = fixtureInfo?.league?.season ?: -1
            ),
            score = ScoreUIModel(
                fulltime = GoalsUIModel(
                    home = fixtureInfo?.score?.fulltime?.home?.toString() ?: "V",
                    away = fixtureInfo?.score?.fulltime?.away?.toString() ?: "V"
                ),
                halftime = GoalsUIModel(
                    home = fixtureInfo?.score?.halftime?.home?.toString() ?: "V",
                    away = fixtureInfo?.score?.halftime?.away?.toString() ?: "V"
                ),
                extratime = GoalsUIModel(
                    home = fixtureInfo?.score?.extratime?.home?.toString() ?: "V",
                    away = fixtureInfo?.score?.extratime?.away?.toString() ?: "V"
                ),
                penalty = GoalsUIModel(
                    home = fixtureInfo?.score?.penalty?.home?.toString() ?: "V",
                    away = fixtureInfo?.score?.penalty?.away?.toString() ?: "V"
                )
            ),
            teams = FixtureTeamsUIModel(
                away = FixtureTeamsInfoUIModel(
                    id = fixtureInfo?.teams?.away?.id ?: -1,
                    logo = fixtureInfo?.teams?.away?.logo.orEmpty(),
                    name = fixtureInfo?.teams?.away?.name.orEmpty(),
                    winner = fixtureInfo?.teams?.away?.winner ?: false
                ),
                home = FixtureTeamsInfoUIModel(
                    id = fixtureInfo?.teams?.home?.id ?: -1,
                    logo = fixtureInfo?.teams?.home?.logo.orEmpty(),
                    name = fixtureInfo?.teams?.home?.name.orEmpty(),
                    winner = fixtureInfo?.teams?.home?.winner ?: false
                )
            )
        )

    }
}