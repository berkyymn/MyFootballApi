package com.berkayyaman.footballapp.data.remote.mapper

import com.berkayyaman.footballapp.data.remote.dto.TeamInfo
import com.berkayyaman.footballapp.data.remote.dto.TeamsResponse
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
}