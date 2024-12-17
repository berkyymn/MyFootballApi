package com.berkayyaman.footballapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.berkayyaman.footballapp.domain.model.TeamUiModel
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme
import com.berkayyaman.footballapp.util.Dimens
import com.berkayyaman.footballapp.util.MockData

/**
 * Created by berkayyaman on 10,December,2024
 */

@Composable
fun FavoriteTeamSummaryView(
    teamUiModel: TeamUiModel
){

    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(Dimens.SmallSpace),
        verticalAlignment = Alignment.CenterVertically
    ){

        AsyncImage(
            modifier = Modifier
                .size(Dimens.TeamLogoRowSize*2)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(
                context = LocalContext.current
            ).data(teamUiModel.teamInfo.logo).build(),
            contentDescription = teamUiModel.teamInfo.name,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .padding(start = Dimens.SmallSpace)
        ){
            Column {
                Text(
                    textAlign = TextAlign.Center,
                    text = teamUiModel.teamInfo.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    textAlign = TextAlign.Center,
                    text = "${teamUiModel.teamInfo.country} | ${teamUiModel.venue.city} | ${teamUiModel.teamInfo.founded}",
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    textAlign = TextAlign.Justify,
                    text = "${teamUiModel.venue.name} | Kapasite: ${teamUiModel.venue.capacity}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }

}


@Preview
@Composable
private fun FavoriteTeamSummaryViewPreview(){
    FootballAppTheme {
        Surface {
            FavoriteTeamSummaryView(
                teamUiModel = MockData.teamUiModel
            )
        }
    }
}