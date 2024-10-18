package com.berkayyaman.footballapp.presentation.favoriteTeam

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.berkayyaman.footballapp.R
import com.berkayyaman.footballapp.domain.model.TeamUiModel
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme
import com.berkayyaman.footballapp.util.Dimens
import com.berkayyaman.footballapp.util.MockData

/**
 * Created by berkayyaman on 18,October,2024
 */

@Composable
fun FavoriteTeamInfoRow(
    teamUiModel: TeamUiModel,
    event: (FavoriteTeamEvent) -> Unit
) {

    MyView(
        teamUiModel = teamUiModel
    ) {
        event(FavoriteTeamEvent.TeamClicked(teamUiModel))
    }

}

@Composable
private fun MyView(
    teamUiModel: TeamUiModel,
    event: (FavoriteTeamEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                event.invoke(FavoriteTeamEvent.TeamClicked(teamUiModel))
            }
            .fillMaxWidth()
            .padding(Dimens.SmallSpace),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        AsyncImage(
            modifier = Modifier
                .size(Dimens.TeamLogoRowSize)
                .clip(MaterialTheme.shapes.small),
            model = ImageRequest.Builder(LocalContext.current)
                .data(teamUiModel.teamInfo.logo)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.size(Dimens.SmallSpace))

        Column {
            Text(
                text = teamUiModel.teamInfo.name,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(R.color.headline)
            )

            Text(
                text = teamUiModel.teamInfo.country,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(R.color.headline)
            )
        }

    }
}

@Composable
@Preview
private fun FavoriteTeamInfoRowPreview() {

    FootballAppTheme {
        Surface {
            MyView(
                teamUiModel = MockData.teamUiModel,
                event = {}
            )
        }
    }
}